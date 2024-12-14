package vn.iotstar.utescore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "booking_id", referencedColumnName = "BookingID", insertable = false, updatable = false)
	private Booking booking; // Liên kết với bảng Booking

	@Column(name = "payment_date", nullable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
	private LocalDateTime paymentDate = LocalDateTime.now();

	@Column(name = "amount", nullable = false, precision = 10, scale = 2)
	private BigDecimal amount;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private PaymentStatus status = PaymentStatus.PENDING;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false) // Tạo khóa ngoại liên kết với bảng Users
	private User user; // Người thanh toán

	// Getter và Setter cho các trường
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public void setUserId(int userId) {
		this.user = new User();
		this.user.setUserId(userId);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public enum PaymentStatus {
		PENDING, COMPLETED, FAILED
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", booking=" + booking + ", paymentDate=" + paymentDate + ", amount=" + amount
				+ ", status=" + status + ", user=" + user + "]";
	}
}
