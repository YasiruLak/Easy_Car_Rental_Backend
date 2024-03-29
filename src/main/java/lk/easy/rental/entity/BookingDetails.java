package lk.easy.rental.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/3/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@IdClass(BookingDetails_PK.class)
public class  BookingDetails {
    @Id
    private String vehicleId;
    @Id
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "vehicleId",referencedColumnName = "vehicleId",insertable = false, updatable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "bookingId",referencedColumnName = "bookingId",insertable = false, updatable = false)
    private Booking booking;
}
