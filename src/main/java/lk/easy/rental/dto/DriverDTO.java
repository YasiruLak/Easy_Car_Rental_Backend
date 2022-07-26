package lk.easy.rental.dto;

import lk.easy.rental.embeded.Name;
import lk.easy.rental.enums.AvailabilityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


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

    private String id;
    private String nic;
    private Name name;
    private String address;
    private String drivingLicenseNo;
    private String email;
    private String contactNo;
    private UserDTO user;
    private AvailabilityType driverAvailability;

}
