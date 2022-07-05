package lk.easy.rental.controller;

import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.UserDTO;
import lk.easy.rental.service.UserService;
import lk.easy.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveUser(@RequestBody UserDTO userDTO){
        userService.saveUser(userDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllUsers(){
        return new ResponseUtil(200,"OK",userService.getAllUsers());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateUser(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
        return new ResponseUtil(200,"Updated",null);
    }

    @GetMapping(path = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchUser(@PathVariable String userName){
        UserDTO userDTO = userService.searchUser(userName);
        return new ResponseUtil(200,"Loaded", userDTO);
    }

    @DeleteMapping(params = {"userName"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteUser(@RequestParam String userName){
        userService.deleteUser(userName);
        return new ResponseUtil(200,"Deleted",null);
    }
}
