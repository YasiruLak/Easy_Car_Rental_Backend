package lk.easy.rental.service.impl;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/2/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public void deleteCustomer(String id) {

    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {

    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        return null;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return null;
    }
}
