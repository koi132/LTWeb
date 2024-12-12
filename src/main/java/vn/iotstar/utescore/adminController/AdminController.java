package vn.iotstar.utescore.adminController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.el.stream.Optional;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.iotstar.utescore.entity.Booking;
import vn.iotstar.utescore.repository.BookingRepository;
import vn.iotstar.utescore.services.BookingService;

@Controller
@RequestMapping("/")
public class AdminController {
	
	@Autowired
    private BookingRepository bookingRepository;
	
	@Autowired private BookingService bookingService;
	
	@RequestMapping("/admin")
	public String manageFields(@RequestParam(value = "selectedDate", required = false) LocalDate selectedDate, Model model) {
	    // Nếu selectedDate không có giá trị, gán giá trị mặc định là ngày hiện tại
	    if (selectedDate == null) {
	        selectedDate = LocalDate.now();
	    }

	    // Lấy thời gian hiện tại
	    LocalDate currentDate = LocalDate.now();
	    LocalTime currentTime = LocalTime.now();

	    // Lấy tất cả các booking cho các sân tại thời điểm hiện tại
	    java.util.List<Booking> bookings = bookingRepository.findBookingsForCurrentTime(currentDate, currentTime);

	    // Mảng để lưu trạng thái của 5 sân
	    Booking[] fields = new Booking[5];

	    // Lặp qua các booking và gán chúng vào các sân tương ứng
	    for (Booking booking : bookings) {
	        int fieldId = booking.getThongTinSan().getFieldID();
	        fields[fieldId - 1] = booking;
	    }

	    model.addAttribute("fields", fields);

	    // Lấy tất cả bookings cho ngày đã chọn
	    java.util.List<Booking> Datebookings = bookingRepository.findByBookingDate(selectedDate);

	    // Sắp xếp các booking theo sân (ưu tiên sân 1 trước) và thời gian từ sớm tới trễ
	    Datebookings.sort((b1, b2) -> {
	        // Ưu tiên Sân 1 trước
	        if (b1.getThongTinSan().getFieldID() == 1 && b2.getThongTinSan().getFieldID() != 1) return -1;
	        if (b1.getThongTinSan().getFieldID() != 1 && b2.getThongTinSan().getFieldID() == 1) return 1;

	        // Nếu cả hai đều thuộc cùng một sân, sắp xếp theo thời gian
	        if (b1.getThongTinSan().getFieldID() == b2.getThongTinSan().getFieldID()) {
	            return b1.getEndTime().compareTo(b2.getEndTime()); // Sắp xếp theo thời gian booking
	        }

	        // Nếu không, sắp xếp theo FieldID (Sân 1 trước, sân 2 sau)
	        return Integer.compare(b1.getThongTinSan().getFieldID(), b2.getThongTinSan().getFieldID());
	    });

	    // Thêm dữ liệu vào model
	    model.addAttribute("Datebookings", Datebookings);
	    model.addAttribute("selectedDate", selectedDate);

	    return "admin/qlsb"; // Trang hiển thị
	}

	@RequestMapping("/admin/seen")
	public String processSeen(@RequestParam("selectedDate") LocalDate selectedDate, Model model) {
	    // Xử lý tại trang /admin/seen
	    // Ví dụ: Lấy thông tin booking cho ngày đã chọn
	    java.util.List<Booking> Datebookings = bookingRepository.findByBookingDate(selectedDate);

	    // Sắp xếp các booking theo sân (ưu tiên sân 1 trước) và thời gian từ sớm tới trễ
	    Datebookings.sort((b1, b2) -> {
	        // Ưu tiên Sân 1 trước
	        if (b1.getThongTinSan().getFieldID() == 1 && b2.getThongTinSan().getFieldID() != 1) return -1;
	        if (b1.getThongTinSan().getFieldID() != 1 && b2.getThongTinSan().getFieldID() == 1) return 1;

	        // Nếu cả hai đều thuộc cùng một sân, sắp xếp theo thời gian
	        if (b1.getThongTinSan().getFieldID() == b2.getThongTinSan().getFieldID()) {
	            return b1.getEndTime().compareTo(b2.getEndTime()); // Sắp xếp theo thời gian booking
	        }

	        // Nếu không, sắp xếp theo FieldID (Sân 1 trước, sân 2 sau)
	        return Integer.compare(b1.getThongTinSan().getFieldID(), b2.getThongTinSan().getFieldID());
	    });

	    // Thêm dữ liệu vào model
	    model.addAttribute("Datebookings", Datebookings);
	    model.addAttribute("selectedDate", selectedDate);


	    // Sau khi xử lý xong, redirect về trang /admin/qlsb
	    return "redirect:/admin?selectedDate=" + selectedDate;
	}

	@RequestMapping("/search")
	public String search(@RequestParam("query") String query, Model model) {
	    // In giá trị query ra console để kiểm tra
	    System.out.println("Query: " + query);
	    
	    // Tìm kiếm bookings theo tên khách hàng hoặc số điện thoại
	    java.util.List<Booking> searchResults = bookingRepository.findByCustomerNameContainingIgnoreCaseOrPhone(query, query);

	    // Thêm kết quả tìm kiếm vào model để hiển thị
	    model.addAttribute("searchResults", searchResults);
	    model.addAttribute("query", query);  // Thêm query vào model để hiển thị trên trang

	    System.out.println("Kết quả tìm kiếm: " + searchResults);

	    // Trả về trang kết quả tìm kiếm
	    return "admin/search"; // Tên trang hiển thị kết quả tìm kiếm
	}

	@PostMapping("/updateStatus")
	public @ResponseBody Map<String, Object> updateBookingStatus(@RequestParam("bookingID") int bookingID, 
	                                                             @RequestParam("bookingCode") String bookingCode) {
	    Map<String, Object> response = new HashMap<>();  // Tạo đối tượng Map để chứa kết quả
	    Booking booking = bookingService.updateBookingStatus(bookingID, bookingCode);  // Cập nhật trạng thái đặt sân

	    if (booking != null) {
	        response.put("success", true);  // Thêm thông tin vào Map
	        response.put("message", "Trạng thái đã được cập nhật.");
	    } else {
	        response.put("success", false);
	        response.put("message", "Mã booking không hợp lệ.");
	    }

	    return response;  // Trả về phản hồi dạng JSON
	}








}
