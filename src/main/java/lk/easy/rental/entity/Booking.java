package lk.easy.rental.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate pickUpDate;
    @Column(name = "pick_up_time")
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime pickUpTime;
    @Column(name = "return_date")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate returnDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "driver_request_type")
    private RequestType DriverRequestType;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name = "custId",referencedColumnName = "customer_id",nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "booking")
    private List<BookingDetails> bookingDetails;

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
    private List<DriverSchedule> driverSchedules;



}
