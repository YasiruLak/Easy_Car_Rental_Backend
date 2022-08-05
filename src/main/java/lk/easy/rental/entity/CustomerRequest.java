package lk.easy.rental.entity;

import lk.easy.rental.embeded.Name;
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
public class CustomerRequest {

    @Id
    private String id;
    @Column(name = "nic_no")
    private String nic;
    @Embedded
    @Column(name = "name")
    private Name name;
    @Column(name = "address")
    private String address;
    @Column(name = "driving_license_no")
    private String drivingLicenseNo;
    @Column(name = "email")
    private String email;
    @Column(name = "contact_No")
    private String contactNo;
    @OneToOne(cascade = CascadeType.ALL)
    private UserRequest user;

    
}
