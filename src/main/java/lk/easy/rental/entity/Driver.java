package lk.easy.rental.entity;

import lk.easy.rental.embeded.Name;
import lk.easy.rental.enums.AvailabilityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
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
@ToString
@Entity
public class Driver {

    @Id
    @Column(name = "driver_id")
    private String driverId;
    @Column(name = "user_name")
    private String driverNIC;
    @Column(name = "license_no")
    private String driverLicenseNo;
    @Embedded
    @Column(name = "name")
    private Name driverName;
    @Column(name = "address")
    private String driverAddress;
    @Column(name = "contact_no")
    private String driverContactNo;
    @Enumerated(EnumType.STRING)
    @Column(name = "availability")
    private AvailabilityType driverAvailability;



}
