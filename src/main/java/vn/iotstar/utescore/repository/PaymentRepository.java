package vn.iotstar.utescore.repository;

import vn.iotstar.utescore.entity.Payment;
import vn.iotstar.utescore.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findByUser(User user);
	
	 // Tính doanh thu hàng tháng
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE MONTH(p.paymentDate) = :month AND YEAR(p.paymentDate) = :year")
    Double calculateMonthlyRevenue(@Param("month") int month, @Param("year") int year);

    // Tính doanh thu hàng năm
    @Query("SELECT SUM(p.amount) FROM Payment p WHERE YEAR(p.paymentDate) = :year")
    Double calculateAnnualRevenue(@Param("year") int year);
}
