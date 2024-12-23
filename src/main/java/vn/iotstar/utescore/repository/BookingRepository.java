package vn.iotstar.utescore.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.iotstar.utescore.entity.Booking;
import vn.iotstar.utescore.entity.Thongtinsan;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findByUserId(int userId);

	// Tìm các booking theo ngày đặt
	List<Booking> findByBookingDate(LocalDate bookingDate);

	// Tìm các booking theo sân (FieldID)
	List<Booking> findByThongTinSan_FieldID(Integer fieldID);

	// Tìm booking theo khách hàng
	List<Booking> findByCustomerNameContaining(String customerName);

	@Query(value = "SELECT * FROM Booking b WHERE b.bookingDate = :date "
			+ "AND CONVERT(time, b.startTime) <= CONVERT(time, :currentTime) "
			+ "AND CONVERT(time, b.endTime) > CONVERT(time, :currentTime)", nativeQuery = true)
	List<Booking> findBookingsForCurrentTime(@Param("date") LocalDate date,
			@Param("currentTime") LocalTime currentTime);

	// Tìm kiếm Booking theo tên khách hàng hoặc số điện thoại
	List<Booking> findByCustomerNameContainingIgnoreCaseOrPhone(String customerName, String phone);

	Optional<Booking> findByBookingCode(String bookingCode);

	// Tìm Booking theo BookingID
	Optional<Booking> findById(int bookingID);

 // Tìm kiếm Booking theo tên khách hàng hoặc số điện thoại
    
    
	 //@Query("SELECT SUM(b.totalPayment) FROM Booking b WHERE MONTH(b.bookingDate) = :month AND YEAR(b.bookingDate) = :year")
	    //BigDecimal calculateMonthlyRevenue(int month, int year);

	   // @Query("SELECT SUM(b.totalPayment) FROM Booking b WHERE YEAR(b.bookingDate) = :year")
	   // BigDecimal calculateAnnualRevenue(int year);
}
