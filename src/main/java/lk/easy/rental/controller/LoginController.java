package lk.easy.rental.controller;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.UserDTO;
import lk.easy.rental.jwt.AuthenticationRequest;
import lk.easy.rental.service.CustomerService;
import lk.easy.rental.service.LoginService;
import lk.easy.rental.service.UserService;
import lk.easy.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/12/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@RestController
@RequestMapping("api/v1/login")
@CrossOrigin
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;

//    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> saveCustomer(@RequestBody AuthenticationRequest authenticationRequest, Principal principal){
//        ResponseEntity<?> responseEntity = userService.logInUser(authenticationRequest);
//        return responseEntity;
//    }

    @GetMapping()
    public ResponseUtil getUser(@RequestBody UserDTO userDTO){
        UserDTO dto = loginService.loginUser(userDTO.getUserName(), userDTO.getPassword());
        return new ResponseUtil(200,"Saved",dto);
    }

}
