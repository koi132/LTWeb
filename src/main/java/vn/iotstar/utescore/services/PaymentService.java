package vn.iotstar.utescore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iotstar.utescore.entity.Payment;
import vn.iotstar.utescore.entity.User;
import vn.iotstar.utescore.repository.PaymentRepository;

import java.util.List;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment createPayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public Payment getPaymentById(Integer id) {
		return paymentRepository.findById(id).orElse(null);
	}

	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	public void deletePayment(Integer id) {
		paymentRepository.deleteById(id);
	}

	public List<Payment> getPaymentsByUser(User user) {
		return paymentRepository.findByUser(user);
	}

	public Payment addPayment(Payment payment) {
		return paymentRepository.save(payment);
	}

}
