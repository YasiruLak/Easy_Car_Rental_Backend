package lk.easy.rental.repo;

import lk.easy.rental.entity.Booking;
import lk.easy.rental.entity.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface BookingRepo extends JpaRepository<Booking,String> {

    List<Booking> findAllByReturnDateIsAfterAndPickUpDateIsBefore(LocalDate pickUpDate, LocalDate returnDate);

    @Query(value = "SELECT bookingId FROM booking ORDER BY bookingId DESC LIMIT 1", nativeQuery = true)
    String generateBookingIds();




}
