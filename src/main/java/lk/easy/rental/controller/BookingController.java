package lk.easy.rental.controller;

import lk.easy.rental.dto.BookingDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.service.BookingService;
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
@RequestMapping("api/v1/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    BookingService bookingService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveBooking(@RequestBody BookingDTO bookingDTO){
        bookingService.saveBooking(bookingDTO);
        return new ResponseUtil(200,"Saved",null);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil getAllBooking(){
        return new ResponseUtil(200,"OK",bookingService.getAllBooking());
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateBooking(@RequestBody BookingDTO bookingDTO){
        bookingService.updateBooking(bookingDTO);
        return new ResponseUtil(200,"Updated",null);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil searchBooking(@PathVariable String id){
        BookingDTO bookingDTO = bookingService.searchBooking(id);
        return new ResponseUtil(200,"Loaded", bookingDTO);
    }

    @DeleteMapping(params = {"id"},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil deleteBooking(@RequestParam String id){
        bookingService.deleteBooking(id);
        return new ResponseUtil(200,"Deleted",null);
    }
}
