package vn.iotstar.utescore.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    // Constructors
    public Booking() {
    }

    public Booking(Thongtinsan thongTinSan, String customerName, String phone, LocalDate bookingDate, LocalTime startTime, LocalTime endTime) {
        this.thongTinSan = thongTinSan;
        this.customerName = customerName;
        this.phone = phone;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
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

	@Override
	public String toString() {
		return "Booking [bookingID=" + bookingID + ", thongTinSan=" + thongTinSan + ", customerName=" + customerName
				+ ", phone=" + phone + ", bookingDate=" + bookingDate + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
    
}
