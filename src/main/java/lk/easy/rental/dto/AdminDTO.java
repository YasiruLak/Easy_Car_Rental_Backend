package lk.easy.rental.dto;

import lk.easy.rental.embeded.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/

@Data
@ToString
public class AdminDTO {

    private String adminId;
    private String adminNic;
    private Name adminName;
    private String adminAddress;
    private String adminEmail;
    private String adminContact;
    private UserDTO user;

    public AdminDTO(String adminId, String adminNic, Name adminName, String adminAddress, String adminEmail, String adminContact, UserDTO user) {
        this.adminId = adminId;
        this.adminNic = adminNic;
        this.adminName = adminName;
        this.adminAddress = adminAddress;
        this.adminEmail = adminEmail;
        this.adminContact = adminContact;
        this.user = user;
    }

    public AdminDTO() {
    }
}
