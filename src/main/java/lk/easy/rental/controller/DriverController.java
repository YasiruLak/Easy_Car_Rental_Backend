package lk.easy.rental.controller;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.service.DriverService;
import lk.easy.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/4/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@RestController
@RequestMapping("api/v1/driver")
@CrossOrigin
public class DriverController {

    @Autowired
    DriverService driverService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveDriver(@RequestBody DriverDTO driverDTO){
        driverService.saveDriver(driverDTO);
        System.out.println(driverDTO.toString());
        return new ResponseUtil(200,"Saved",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllDrivers(){
        return new ResponseUtil(200,"OK",driverService.getAllDriver());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateDriver(@RequestBody DriverDTO driverDTO){
        driverService.updateDriver(driverDTO);
        System.out.println(driverDTO.toString());
        return new ResponseUtil(200,"Updated",null);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchDriver(@PathVariable String id){
        DriverDTO driverDTO = driverService.searchDriver(id);
        return new ResponseUtil(200,"Loaded", driverDTO);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteDriver(@RequestParam String id){
        driverService.deleteDriver(id);
        return new ResponseUtil(200,"Deleted",null);
    }
}
