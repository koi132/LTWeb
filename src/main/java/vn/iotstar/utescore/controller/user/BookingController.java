package vn.iotstar.utescore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.iotstar.utescore.entity.Booking;
import vn.iotstar.utescore.services.BookingService;

import java.util.List;

@Controller
@RequestMapping("/user/history")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping("/{userId}")
	public String getUserBookingHistory(@PathVariable int userId, Model model) {
		List<Booking> bookings = bookingService.getBookingsByUserId(userId);
		model.addAttribute("bookings", bookings);
		return "user/yard-history-list";
	}

}
