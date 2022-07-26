package lk.easy.rental.service.impl;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.entity.Customer;
import lk.easy.rental.entity.Driver;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.repo.CustomerRepo;
import lk.easy.rental.repo.DriverRepo;
import lk.easy.rental.repo.UserRepo;
import lk.easy.rental.service.SignUpService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/26/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public void saveDriver(DriverDTO driverDTO) {
        if (!driverRepo.existsById(driverDTO.getId())) {
            if (!userRepo.existsByUserName(driverDTO.getUser().getUserName())) {
                driverRepo.save(mapper.map(driverDTO, Driver.class));
            } else {
                throw new DuplicateEntryException("User Name Already Exists");
            }
        }else{
            throw new DuplicateEntryException("Driver Already Exists");
        }
    }

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        if (!customerRepo.existsById(customerDTO.getId())) {
            if (!userRepo.existsByUserName(customerDTO.getUser().getUserName())) {
                customerRepo.save(mapper.map(customerDTO, Customer.class));
            } else {
                throw new DuplicateEntryException("User Name Already Exists");
            }
        }else{
            throw new DuplicateEntryException("Customer Already Exists");
        }
    }

}
