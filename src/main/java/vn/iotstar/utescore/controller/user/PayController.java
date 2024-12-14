package vn.iotstar.utescore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.iotstar.utescore.entity.Booking;
import vn.iotstar.utescore.entity.Payment;
import vn.iotstar.utescore.entity.Thongtinsan;
import vn.iotstar.utescore.entity.User;
import vn.iotstar.utescore.services.BookingService;
import vn.iotstar.utescore.services.PaymentService;
import vn.iotstar.utescore.services.ThongTinSanService;
import vn.iotstar.utescore.services.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/user/yard")
public class PayController {
	@Autowired
	private ThongTinSanService thongTinSanService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserService userService;

	@GetMapping("/details/{id}")
	public String getYardDetails(@PathVariable int id, Model model) {
		Thongtinsan yard = thongTinSanService.getThongTinSanById(id);
		model.addAttribute("yard", yard);
		return "user/yard-detail";
	}

	@PostMapping("/book")
	public String bookYard(@RequestParam int yardId, @RequestParam String customerName, @RequestParam String phone,
			@RequestParam String bookingDate, @RequestParam String startTime, @RequestParam String endTime,
			@RequestParam int userId) {
		Thongtinsan yard = thongTinSanService.getThongTinSanById(yardId);
		yard.setStatus("booked");
		thongTinSanService.updateThongTinSan(yard);
		Booking booking = new Booking(yard, customerName, phone, LocalDate.parse(bookingDate),
				LocalTime.parse(startTime), LocalTime.parse(endTime), userId);
		bookingService.addBooking(booking);
		User user = userService.getUserById(userId);
		Payment payment = new Payment();
		payment.setBooking(booking);
		payment.setAmount(BigDecimal.valueOf(yard.getPrice()));
		payment.setUserId(1);
		paymentService.addPayment(payment);
		return "redirect:/yard/details/" + yardId;
	}
}
