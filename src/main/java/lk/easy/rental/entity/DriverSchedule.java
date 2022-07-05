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
@IdClass(DriverSchedule_PK.class)
public class DriverSchedule {

    @Id
    @Column(name = "driver_id")
    private String driverId;
    @Id
    @Column(name = "booking_id")
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "driverId",referencedColumnName = "driver_id",insertable = false,updatable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "bookingId",referencedColumnName = "booking_id",insertable = false,updatable = false)
    private Booking booking;

}
