package lk.easy.rental.service;

import lk.easy.rental.dto.UserDTO;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface UserService {

    void saveUser(UserDTO userDTO);
    void deleteUser(String userName);
    void updateUser(UserDTO userDTO);
    UserDTO searchUser(String userName);
    List<UserDTO> getAllUsers();
}
