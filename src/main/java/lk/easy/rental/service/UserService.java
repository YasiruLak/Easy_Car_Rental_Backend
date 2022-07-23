package lk.easy.rental.service;

import lk.easy.rental.dto.UserDTO;
import lk.easy.rental.jwt.AuthenticationRequest;
import org.springframework.http.ResponseEntity;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/12/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface UserService {

    ResponseEntity<?> logInUser(AuthenticationRequest authenticationRequest);

}
