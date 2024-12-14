package vn.iotstar.utescore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.utescore.entity.Payment;
import vn.iotstar.utescore.entity.User;
import vn.iotstar.utescore.services.PaymentService;
import vn.iotstar.utescore.services.UserService;

import java.util.List;

@Controller // Đổi @RestController thành @Controller
@RequestMapping("/user/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserService userService;

	@PostMapping
	@ResponseBody // Dùng @ResponseBody để trả về JSON cho API POST
	public Payment createPayment(@RequestBody Payment payment) {
		return paymentService.createPayment(payment);
	}

	@GetMapping("/{id}")
	@ResponseBody // Dùng @ResponseBody để trả về JSON cho API GET /{id}
	public Payment getPaymentById(@PathVariable Integer id) {
		return paymentService.getPaymentById(id);
	}

	@GetMapping
	@ResponseBody // Dùng @ResponseBody để trả về JSON cho API GET tất cả payments
	public List<Payment> getAllPayments() {
		return paymentService.getAllPayments();
	}

	@DeleteMapping("/{id}")
	@ResponseBody // Dùng @ResponseBody để trả về JSON cho API DELETE
	public void deletePayment(@PathVariable Integer id) {
		paymentService.deletePayment(id);
	}

	@GetMapping("/list")
	public String listPayments(Model model) {
		List<Payment> payments = paymentService.getAllPayments();
		model.addAttribute("payments", payments);
		return "user/payment-list";
	}

	@GetMapping("/list/{userId}")
	public String listPaymentsByUserId(@PathVariable int userId, Model model) {
		User user = userService.getUserById(userId);
		List<Payment> payments = paymentService.getPaymentsByUser(user);
		model.addAttribute("payments", payments);
		return "user/payment-list";
	}
}
