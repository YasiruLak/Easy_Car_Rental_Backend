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
public class Customer {

    @Id
    private String customerId;
    @Column(name = "nic_no")
    private String customerNIC;
    @Embedded
    @Column(name = "name")
    private Name customerName;
    @Column(name = "address")
    private String customerAddress;
    @Column(name = "driving_license_no")
    private String customerDrivingLicenseNo;
    @Column(name = "email")
    private String customerEmail;
    @Column(name = "contact_No")
    private String customerContactNo;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    
}
