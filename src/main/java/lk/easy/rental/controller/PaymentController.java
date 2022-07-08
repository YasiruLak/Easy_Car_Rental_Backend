package lk.easy.rental.controller;

import lk.easy.rental.dto.PaymentDTO;
import lk.easy.rental.service.PaymentService;
import lk.easy.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/8/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("api/v1/payment")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil savePayment(@RequestBody PaymentDTO paymentDTO){
        paymentService.savePayment(paymentDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllPayment(){
        return new ResponseUtil(200,"OK",paymentService.getAllPayment());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updatePayment(@RequestBody PaymentDTO paymentDTO){
        paymentService.updatePayment(paymentDTO);
        return new ResponseUtil(200,"Updated",null);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchPayment(@PathVariable String id){
        PaymentDTO paymentDTO = paymentService.searchPayment(id);
        return new ResponseUtil(200,"Loaded", paymentDTO);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deletePayment(@RequestParam String id){
        paymentService.deletePayment(id);
        return new ResponseUtil(200,"Deleted",null);
    }
}
