package vn.iotstar.utescore.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iotstar.utescore.entity.Payment;
import vn.iotstar.utescore.services.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/user/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
		Payment createdPayment = paymentService.createPayment(payment);
		return ResponseEntity.ok(createdPayment);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable Integer id) {
		Payment payment = paymentService.getPaymentById(id);
		return ResponseEntity.ok(payment);
	}

	@GetMapping
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> payments = paymentService.getAllPayments();
		return ResponseEntity.ok(payments);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePayment(@PathVariable Integer id) {
		paymentService.deletePayment(id);
		return ResponseEntity.noContent().build();
	}
}
