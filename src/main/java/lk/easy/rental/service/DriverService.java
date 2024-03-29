package lk.easy.rental.service;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;

import java.time.LocalDate;
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
    String generateDriverIds();
    long countDrivers();
    DriverDTO getDriverInUserName(String userName);
    DriverDTO loadAvailableDriver(LocalDate pickupDate, LocalDate dropOffDate);
    DriverDTO getAvailableDriver();
}
