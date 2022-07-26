package lk.easy.rental.controller;

import lk.easy.rental.embeded.PriceRate;
import lk.easy.rental.enums.FuelType;
import lk.easy.rental.service.BrowseService;
import lk.easy.rental.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("api/v1/browse")
@CrossOrigin
public class BrowseController {

    @Autowired
    BrowseService browseService;

    @GetMapping(params = {"noOfPassenger"})
    public ResponseUtil sortVehicleNoOfPassenger(@RequestParam int noOfPassenger){
        return new ResponseUtil(200,"OK",browseService.getAllNoOfPassenger(noOfPassenger));
    }

    @GetMapping(params = {"fuelType"})
    public ResponseUtil sortVehicleFuelType(@RequestParam FuelType fuelType){
        return new ResponseUtil(200,"OK",browseService.getAllFuelType(fuelType));
    }

    @GetMapping(params = {"priceRate"})
    public ResponseUtil sortVehiclePriceRate(@RequestParam PriceRate priceRate){
        return new ResponseUtil(200,"OK",browseService.getPriceRate(priceRate));
    }
}
