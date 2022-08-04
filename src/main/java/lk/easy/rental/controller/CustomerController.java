package lk.easy.rental.controller;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.service.CustomerService;
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
 * @date : 7/3/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllCustomers(){
        return new ResponseUtil(200,"OK",customerService.getAllCustomer());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO);
        return new ResponseUtil(200,"Updated",null);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchCustomer(@PathVariable String id){
        CustomerDTO customerDTO = customerService.searchCustomer(id);
        return new ResponseUtil(200,"Loaded", customerDTO);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteCustomer(@RequestParam String id){
        customerService.deleteCustomer(id);
        return new ResponseUtil(200,"Deleted",null);
    }

    @GetMapping(params = {"test"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil generateCustomersIds(@RequestParam String test) {
        return new ResponseUtil(200, "Ok", customerService.generateCustomerIds());
    }

    @GetMapping(path ="/COUNT/{count}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil countCustomers(@PathVariable String count){
        return new ResponseUtil(200, "Ök", customerService.countCustomer());
    }

    @SneakyThrows
    @DeleteMapping(path = "deleteCustomerImage", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteAllCustomerImages(@RequestParam String id) {
        String pathDirectory = "F:\\Project\\Web-Second Sem\\Spring Projects\\Easy Car Rental System\\Car_Rental_Backend\\src\\main\\resources\\static\\image\\registerImage\\";

        String[] registerImageView = {"Nic", "Licence"};

        for (int i = 0; i < registerImageView.length; i++) {
            Files.deleteIfExists(Paths.get(pathDirectory + File.separator + id + registerImageView[i] + ".jpeg"));
        }
        return new ResponseUtil(200, "Customer Data Delete success", null);
    }
}
