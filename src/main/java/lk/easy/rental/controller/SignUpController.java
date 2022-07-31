package lk.easy.rental.controller;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.service.SignUpService;
import lk.easy.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/26/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@RestController
@RequestMapping("api/v1/signup")
@CrossOrigin
public class SignUpController {

    @Autowired
    SignUpService signUpService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/driver")
    public ResponseUtil saveDriver(@RequestBody DriverDTO driverDTO){
        signUpService.saveDriver(driverDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customer")
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO){
        signUpService.saveCustomer(customerDTO);
        return new ResponseUtil(200,"Saved",null);
    }

}


