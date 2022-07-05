package lk.easy.rental.service;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.UserDTO;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/2/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface CustomerService {

    void saveCustomer(CustomerDTO customerDTO, UserDTO userDTO);
    void deleteCustomer(String id);
    void updateCustomer(CustomerDTO customerDTO, UserDTO userDTO);
    CustomerDTO searchCustomer(String id);
    List<CustomerDTO> getAllCustomer();
}
