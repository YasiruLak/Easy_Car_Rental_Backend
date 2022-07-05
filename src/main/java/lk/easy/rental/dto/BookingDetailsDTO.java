package lk.easy.rental.dto;

import lk.easy.rental.entity.Booking;
import lk.easy.rental.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BookingDetailsDTO {

    private String vehicleId;
    private String bookingId;
    private VehicleDTO vehicle;
    private BookingDTO booking;

}
