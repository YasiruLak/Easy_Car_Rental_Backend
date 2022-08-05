package lk.easy.rental.controller;

import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.service.DriverService;
import lk.easy.rental.util.ResponseUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        return new ResponseUtil(200,"Saved",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllDrivers(){
        return new ResponseUtil(200,"OK",driverService.getAllDriver());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateDriver(@RequestBody DriverDTO driverDTO){
        driverService.updateDriver(driverDTO);
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

    @GetMapping(params = {"test"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateDriverIds(@RequestParam String test) {
        return new ResponseUtil(200, "Ok", driverService.generateDriverIds());
    }

    @GetMapping(path ="/driverCount/{count}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil countDrivers(@PathVariable String count){
        return new ResponseUtil(200, "Ok", driverService.countDrivers());
    }

    @GetMapping(params = {"userName"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getDriverInUserName(@RequestParam String userName) {
        return new ResponseUtil(200, "Ok", driverService.getDriverInUserName(userName));
    }

    @SneakyThrows
    @DeleteMapping(path = "deleteDriverImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteAllCustomerImages(@RequestParam String id) {
        String pathDirectory = "F:\\Project\\Web-Second Sem\\Spring Projects\\Easy Car Rental System\\Car_Rental_Backend\\src\\main\\resources\\static\\image\\registerImage\\";

        String[] registerImageView = {"Nic", "Licence"};

        for (int i = 0; i < registerImageView.length; i++) {
            Files.deleteIfExists(Paths.get(pathDirectory + File.separator + id + registerImageView[i] + ".jpeg"));
        }
        return new ResponseUtil(200, "Driver Data Delete success", null);
    }

    @GetMapping( "getAvailableDriver")
    public ResponseUtil getAvailableDriver(){
        return new ResponseUtil(200,"OK", driverService.getAvailableDriver());
    }
}
