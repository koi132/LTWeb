package vn.iotstar.utescore.controller.manager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import org.apache.el.stream.Optional;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.iotstar.utescore.entity.Booking;
import vn.iotstar.utescore.entity.Payment;
import vn.iotstar.utescore.entity.Thongtinsan;
import vn.iotstar.utescore.repository.BookingRepository;
import vn.iotstar.utescore.services.BookingService;
import vn.iotstar.utescore.services.PaymentService;
import vn.iotstar.utescore.services.ThongTinSanService;

@Controller
@RequestMapping("/")
public class ManagerController {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private ThongTinSanService thongTinSanService;

	@RequestMapping("/manager")
	public String manageFields(@RequestParam(value = "selectedDate", required = false) LocalDate selectedDate,
			Model model) {
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

		// Sắp xếp các booking theo sân (ưu tiên sân 1 trước) và thời gian từ sớm tới
		// trễ
		Datebookings.sort((b1, b2) -> {
			// Ưu tiên Sân 1 trước
			if (b1.getThongTinSan().getFieldID() == 1 && b2.getThongTinSan().getFieldID() != 1)
				return -1;
			if (b1.getThongTinSan().getFieldID() != 1 && b2.getThongTinSan().getFieldID() == 1)
				return 1;

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


		
		return "manager/qlsb"; // Trang hiển thị



	}

	@RequestMapping("/manager/seen")
	public String processSeen(@RequestParam("selectedDate") LocalDate selectedDate, Model model) {
		// Xử lý tại trang /admin/seen
		// Ví dụ: Lấy thông tin booking cho ngày đã chọn
		java.util.List<Booking> Datebookings = bookingRepository.findByBookingDate(selectedDate);

		// Sắp xếp các booking theo sân (ưu tiên sân 1 trước) và thời gian từ sớm tới
		// trễ
		Datebookings.sort((b1, b2) -> {
			// Ưu tiên Sân 1 trước
			if (b1.getThongTinSan().getFieldID() == 1 && b2.getThongTinSan().getFieldID() != 1)
				return -1;
			if (b1.getThongTinSan().getFieldID() != 1 && b2.getThongTinSan().getFieldID() == 1)
				return 1;

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
		return "redirect:/manager?selectedDate=" + selectedDate;
	}

	@RequestMapping("/search")
	public String search(@RequestParam("query") String query, Model model) {
		// In giá trị query ra console để kiểm tra
		System.out.println("Query: " + query);

		// Tìm kiếm bookings theo tên khách hàng hoặc số điện thoại
		java.util.List<Booking> searchResults = bookingRepository.findByCustomerNameContainingIgnoreCaseOrPhone(query,
				query);

		// Thêm kết quả tìm kiếm vào model để hiển thị
		model.addAttribute("searchResults", searchResults);
		model.addAttribute("query", query); // Thêm query vào model để hiển thị trên trang

		System.out.println("Kết quả tìm kiếm: " + searchResults);

		// Trả về trang kết quả tìm kiếm
		return "manager/search"; // Tên trang hiển thị kết quả tìm kiếm
	}


	@GetMapping("/add")
    public String showAddYardPage() {
        return "manager/AddYard";  // Trả về file AddYard.html
    }
	@PostMapping("/add1")
	public String addThongTinSan(@RequestParam String fieldName, @RequestParam String type) {
	    // Thêm thông tin sân vào cơ sở dữ liệu
	    //thongTinSanService.addThongTinSan(fieldName, type);
	    
	    // Chuyển hướng đến trang quản lý sân sau khi thêm
	    return "redirect:/manager";  // Sử dụng redirect để chuyển hướng đến trang quản lý sân
	}
	
	@GetMapping("/manager/bookings/delete/{bookingID}")
	public ResponseEntity<String> deleteBooking(@PathVariable int bookingID) {
	    try {
	        bookingService.deleteBooking(bookingID);  // Gọi phương thức delete từ service
	        return ResponseEntity.ok("Xóa lịch đặt sân thành công!");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Không thể xóa lịch đặt sân. Vui lòng thử lại.");
	    }
	}
	
	@PostMapping("/updateStatus")
    public ResponseEntity<Map<String, Object>> updateBookingStatus(
            @RequestParam("bookingID") int bookingID,
            @RequestParam("bookingCode") String bookingCode) {

        // Gọi phương thức từ service để cập nhật trạng thái booking
        Booking updatedBooking = bookingService.updateBookingStatus(bookingID, bookingCode);

        // Tạo một đối tượng phản hồi với thông tin về trạng thái
        Map<String, Object> response = new HashMap<>();
        
        if (updatedBooking != null) {
            response.put("success", true);
            response.put("message", "Cập nhật trạng thái thành công!");
        } else {
            response.put("success", false);
            response.put("message", "Mã đặt sân không hợp lệ hoặc không tồn tại.");
        }

        // Trả về phản hồi với thông báo cho client
        return ResponseEntity.ok(response);
    }
	
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/historyPay")
	public String getAllPayments(Model model) {
	    // Lấy tất cả thanh toán từ service
	    java.util.List<Payment> payments = paymentService.getAllPayments();
	    
	 // In dữ liệu vào console
	    System.out.println("Payments fetched: " + payments);
	    
	    // Thêm dữ liệu vào model
	    model.addAttribute("payments", payments);  // Dữ liệu sẽ được sử dụng trong view
	    
	    // Trả về tên view (JSP hoặc HTML)
	    return "manager/HistoryPay";  // Đây là tên file JSP sẽ được Spring tìm kiếm và trả về
	}

	@GetMapping("/doanhthu")
	public String doanhthu(@RequestParam(defaultValue = "0") int month, 
	                       @RequestParam(defaultValue = "0") int year, Model model) {
	    if (month == 0) {
	        month = java.time.LocalDate.now().getMonthValue(); // Lấy tháng hiện tại nếu không có tham số tháng
	    }
	    if (year == 0) {
	        year = java.time.LocalDate.now().getYear(); // Lấy năm hiện tại nếu không có tham số năm
	    }

	    // Lấy doanh thu hàng tháng và hàng năm
	    BigDecimal monthlyRevenue = bookingService.getMonthlyRevenue(month, year);
	    BigDecimal annualRevenue = bookingService.getAnnualRevenue(year);

	    // Lấy danh sách doanh thu hàng tháng cho biểu đồ
	    java.util.List<BigDecimal> monthlyRevenuesList = bookingService.getMonthlyRevenues(year);
	    
	    // Định dạng giá trị doanh thu với dấu phân cách hàng nghìn và thêm "đồng"
	    DecimalFormat df = new DecimalFormat("#,###");
	    String formattedMonthlyRevenue = df.format(monthlyRevenue) + " đồng";
	    String formattedAnnualRevenue = df.format(annualRevenue) + " đồng";

	    System.out.println("Monthly Revenue: " + monthlyRevenue);
	    System.out.println("Annual Revenue: " + annualRevenue);
	    System.out.println("Monthly Revenues List: " + monthlyRevenuesList);

	    
	    // Đưa dữ liệu vào model
	    model.addAttribute("monthlyRevenue", formattedMonthlyRevenue);
	    model.addAttribute("annualRevenue", formattedAnnualRevenue);

	    return "manager/doanhthu";  // Trả về view
	}
	
}
