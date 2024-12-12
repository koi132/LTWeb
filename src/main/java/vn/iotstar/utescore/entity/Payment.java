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

    @Column(name = "booking_id", nullable = false)
    private Integer bookingId;

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

    

    public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getBookingId() {
		return bookingId;
	}



	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
}
