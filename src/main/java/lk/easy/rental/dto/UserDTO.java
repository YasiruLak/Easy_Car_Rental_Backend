package lk.easy.rental.dto;

import lk.easy.rental.enums.Role;
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
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDTO {

    private int userId;
    private String userName;
    private String password;
    private Role role;

    public UserDTO(String userName, String password, Role role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
