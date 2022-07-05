package lk.easy.rental.service;

import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.VehicleDTO;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface VehicleService {

    void saveVehicle(VehicleDTO vehicleDTO);
    void deleteVehicle(String id);
    void updateVehicle(VehicleDTO vehicleDTO);
    VehicleDTO searchVehicle(String id);
    List<VehicleDTO> getAllVehicle();
}
