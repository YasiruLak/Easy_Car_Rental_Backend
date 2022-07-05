package lk.easy.rental.entity;

import lk.easy.rental.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
public class Booking {

    @Id
    @Column(name = "booking_id")
    private String bookingId;
    @Column(name = "pick_up_date")
    private LocalDate pickUpDate;
    @Column(name = "pick_up_time")
    private LocalTime pickUpTime;
    @Column(name = "return_date")
    private LocalDate returnDate;
    @Column(name = "rental_fee")
    private double rentalFee;
    @Column(name = "damage_fee")
    private double damageFee;
    @Enumerated(EnumType.STRING)
    @Column(name = "driver_request_type")
    private RequestType DriverRequestType;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "custId",referencedColumnName = "customerId",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "booking")
    private List<BookingDetails> bookingDetails;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
    private List<DriverSchedule> driverSchedules;



}
