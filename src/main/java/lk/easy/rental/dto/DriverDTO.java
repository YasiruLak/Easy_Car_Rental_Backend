package lk.easy.rental.dto;

import lk.easy.rental.embeded.Name;
import lk.easy.rental.entity.User;
import lk.easy.rental.enums.AvailabilityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Id;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/4/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DriverDTO {

    private String driverId;
    private String driverNIC;
    private String driverLicenseNo;
    private Name driverName;
    private String driverAddress;
    private String driverContactNo;
    private AvailabilityType driverAvailability;
    private UserDTO user;
}
