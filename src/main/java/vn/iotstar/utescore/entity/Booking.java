package vn.iotstar.utescore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookingID")
    private int bookingID;

    @ManyToOne
    @JoinColumn(name = "FieldID", nullable = false)
    private Thongtinsan thongTinSan;

    @Column(name = "CustomerName", nullable = false, length = 100)
    private String customerName;

    @Column(name = "Phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "BookingDate", nullable = false)
    private LocalDate bookingDate;

    @Column(name = "StartTime", nullable = false)
    private LocalTime startTime;

    @Column(name = "EndTime", nullable = false)
    private LocalTime endTime;

    @Column(name = "booking_code", nullable = false, unique = true, length = 10)
    private String bookingCode;

    @Column(name = "status", nullable = false, length = 20)
    private String status = "Chưa nhận sân";

    @Column(name = "userId", nullable = false)
    private int userId;

 // Các trường khác
    @Column(name = "Total Payment", nullable = true)
    private BigDecimal totalPayment;
    
    // Constructors
    public Booking() {
    }

    public Booking(Thongtinsan thongTinSan, String customerName, String phone, LocalDate bookingDate, LocalTime startTime, LocalTime endTime, int userId, BigDecimal totalPayment) {
        this.thongTinSan = thongTinSan;
        this.customerName = customerName;
        this.phone = phone;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingCode = generateBookingCode();
        this.userId = userId;
        this.totalPayment = totalPayment;
    }


    // Getters and Setters
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Thongtinsan getThongTinSan() {
        return thongTinSan;
    }

    public void setThongTinSan(Thongtinsan thongTinSan) {
        this.thongTinSan = thongTinSan;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @PrePersist
    protected void onCreate() {
        this.bookingCode = generateBookingCode();
    }

    private String generateBookingCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

 // Getters và Setters
    public BigDecimal getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

	@Override
	public String toString() {
		return "Booking [bookingID=" + bookingID + ", thongTinSan=" + thongTinSan + ", customerName=" + customerName
				+ ", phone=" + phone + ", bookingDate=" + bookingDate + ", startTime=" + startTime + ", endTime="
				+ endTime + ", bookingCode=" + bookingCode + ", status=" + status + ", userId=" + userId
				+ ", totalPayment=" + totalPayment + "]";
	}
    
    
}
