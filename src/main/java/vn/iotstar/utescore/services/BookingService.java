package vn.iotstar.utescore.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.iotstar.utescore.entity.Booking;
import vn.iotstar.utescore.repository.BookingRepository;

@Service
public class BookingService {

	@Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public List<Booking> getBookingsByDate(LocalDate date) {
        return bookingRepository.findByBookingDate(date);
    }

    public List<Booking> getBookingsByFieldId(Integer fieldID) {
        return bookingRepository.findByThongTinSan_FieldID(fieldID);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
    
 // Tìm kiếm Booking theo tên khách hàng hoặc số điện thoại
    public List<Booking> searchBookings(String customerName, String phone) {
        return bookingRepository.findByCustomerNameContainingIgnoreCaseOrPhone(customerName, phone);
    }
    
    public Optional<Booking> findByBookingCode(String booking_code) {
        return bookingRepository.findByBookingCode(booking_code);
    }

    public Booking updateBookingStatus(int bookingID, String bookingCode) {
        // Tìm booking theo BookingID
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingID);
        
        // Kiểm tra booking có tồn tại và bookingCode có khớp không
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            if (booking.getBooking_code().equals(bookingCode)) {
                // Cập nhật trạng thái booking
                booking.setStatus("Đã nhận sân");
                return bookingRepository.save(booking);  // Lưu trạng thái mới vào DB
            }
        }
        return null;  // Nếu không tìm thấy hoặc bookingCode không đúng
    }
    public void deleteBooking(int bookingID) {
        bookingRepository.deleteById(bookingID); // Sử dụng trực tiếp phương thức deleteById
    }
    public Optional<Booking> findBookingById(int bookingID) {
        return bookingRepository.findById(bookingID);
    }

    public void updateBooking(Booking booking) {
        bookingRepository.save(booking); // Cập nhật booking vào cơ sở dữ liệu
    }
}
