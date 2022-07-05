package lk.easy.rental.entity;

import lk.easy.rental.embeded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Admin {

    @Id
    @Column(name = "admin_id")
    private String adminId;
    @Column(name = "nic_no")
    private String adminNic;
    @Embedded
    @Column(name = "full_name")
    private Name adminName;
    @Column(name = "address")
    private String adminAddress;
    @Column(name = "email")
    private String adminEmail;
    @Column(name = "contact_no")
    private String adminContact;
}
