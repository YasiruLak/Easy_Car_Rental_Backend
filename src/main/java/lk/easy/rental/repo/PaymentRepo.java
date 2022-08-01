package lk.easy.rental.repo;

import lk.easy.rental.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/8/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface PaymentRepo extends JpaRepository<Payment,String> {

    @Query(value = "SELECT payment_id FROM payment ORDER BY payment_id DESC LIMIT 1", nativeQuery = true)
    String generatePaymentId();
}
