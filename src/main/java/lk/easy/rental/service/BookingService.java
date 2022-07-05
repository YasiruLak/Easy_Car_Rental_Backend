package lk.easy.rental.service;

import lk.easy.rental.dto.BookingDTO;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface BookingService {

    void saveBooking(BookingDTO bookingDTO);
}
