package lk.easy.rental.service;

import lk.easy.rental.dto.BookingDTO;
import lk.easy.rental.dto.VehicleDTO;
import lk.easy.rental.embeded.PriceRate;
import lk.easy.rental.enums.FuelType;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/26/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface BrowseService {

    List<VehicleDTO> getAllNoOfPassenger(int noOfPassenger);

    List<VehicleDTO> getAllFuelType(FuelType fuelType);

    List<VehicleDTO> getPriceRate(PriceRate priceRate);
}
