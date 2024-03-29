package lk.easy.rental.controller;

import lk.easy.rental.dto.AdminDTO;
import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.service.AdminService;
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
@RequestMapping("api/v1/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveAdmin(@RequestBody AdminDTO adminDTO){
        adminService.saveAdmin(adminDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllAdmin(){
        return new ResponseUtil(201,"OK",adminService.getAllAdmin());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateAdmin(@RequestBody AdminDTO adminDTO){
        adminService.updateAdmin(adminDTO);
        return new ResponseUtil(200,"Updated",null);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchAdmin(@PathVariable String id){
        AdminDTO adminDTO = adminService.searchAdmin(id);
        return new ResponseUtil(200,"Loaded", adminDTO);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteAdmin(@RequestParam String id){
        adminService.deleteAdmin(id);
        return new ResponseUtil(200,"Deleted",null);
    }

    @GetMapping(params = {"test"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateAdminIds(@RequestParam String test) {
        return new ResponseUtil(200, "Ok", adminService.generateAdminIds());
    }

    @GetMapping(path ="/adminCount/{count}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil countBooking(@PathVariable String count){
        return new ResponseUtil(200, "Ok", adminService.countAdmin());
    }

    @GetMapping(params = {"userName"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAdminInUserName(@RequestParam String userName) {
        return new ResponseUtil(200, "Ok", adminService.getAdminInUserName(userName));
    }

    @PostMapping("acceptCustomer")
    public ResponseUtil acceptCustomer(@RequestBody CustomerDTO dto){

        adminService.acceptCustomer(dto);
        return new ResponseUtil(200,"OK",null);
    }

    @DeleteMapping( params = {"denyCustomerId"})
    public ResponseUtil denyCustomer(@RequestParam String denyCustomerId){

        adminService.denyCustomer(denyCustomerId);
        return new ResponseUtil(200,"OK",null);
    }

    @PutMapping("notifyMaintenance")
    public ResponseUtil notifyMaintenance(){
        adminService.notifyMaintenance();
        return new ResponseUtil(200,"OK", null);
    }

//    @PutMapping(params = {"id"})
//    public ResponseUtil acceptBookingRequest(@RequestParam String id){
//
//        adminService.acceptBookingRequest(id);
//        return new ResponseUtil(200,"Booking added Successfully",null);
//    }
//
//    @PutMapping( params = {"id","reason"})
//    public ResponseUtil denyBookingRequest(@RequestParam String id,@RequestParam String reason){
//
//        adminService.denyBookingRequest(id,reason);
//        return new ResponseUtil(200,"Booking denied",null);
//    }


}
