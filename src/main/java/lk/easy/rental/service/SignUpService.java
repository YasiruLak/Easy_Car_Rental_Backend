package lk.easy.rental.service;

import lk.easy.rental.dto.AdminDTO;
import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/26/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface SignUpService {
    void saveDriver(DriverDTO driverDTO);
    void saveCustomer(CustomerDTO customerDTO);
}
