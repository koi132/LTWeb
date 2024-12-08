package vn.iotstar.utescore.services;

import java.time.LocalDate;
import java.util.List;

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
}
