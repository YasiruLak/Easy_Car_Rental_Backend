package lk.easy.rental.service;

import lk.easy.rental.dto.DriverDTO;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/4/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface DriverService {

    void saveDriver(DriverDTO driverDTO);
    void deleteDriver(String id);
    void updateDriver(DriverDTO driverDTO);
    DriverDTO searchDriver(String id);
    List<DriverDTO> getAllDriver();
}
