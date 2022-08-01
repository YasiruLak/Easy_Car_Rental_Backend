package lk.easy.rental.service.impl;

import lk.easy.rental.config.PasswordConfig;
import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.entity.Customer;
import lk.easy.rental.entity.Driver;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.CustomerRepo;
import lk.easy.rental.repo.UserRepo;
import lk.easy.rental.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ModelMapper mapper;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    private PasswordConfig passwordConfig;

//    @Override
//    public void saveCustomer(CustomerDTO customerDTO) {
//        if (!customerRepo.existsById(customerDTO.getCustomerId())) {
//            if (!userRepo.existsByUserName(customerDTO.getUser().getUserName())) {
//                Customer customer = mapper.map(customerDTO, Customer.class);
//                customer.getUser().setPassword(passwordConfig.passwordEncoder().encode(customerDTO.getUser().getPassword()));
//                customerRepo.save(customer);
//            } else {
//                throw new DuplicateEntryException("User Name Already Exists");
//            }
//
//        } else {
//            throw new DuplicateEntryException("Customer Already Exists");
//        }
//
//    }

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

    @Override
    public void deleteCustomer(String id) {
        if (customerRepo.existsById(id)) {
            customerRepo.deleteById(id);
        } else {
            throw new NotFoundException("Please check the Customer ID.. No Such Customer..!");
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        if (customerRepo.existsById(customerDTO.getId())) {
            if(!userRepo.existsByUserName(customerDTO.getUser().getUserName())){
                Customer map = mapper.map(customerDTO, Customer.class);
                customerRepo.save(map);
            }

        } else {
            throw new NotFoundException("No Such Customer To Update..! Please Check the ID..!");
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id) {
        if (customerRepo.existsById(id)) {
            return mapper.map(customerRepo.findById(id).get(), CustomerDTO.class);
        } else {
            throw new NotFoundException("No Customer For " + id + " ..!");
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        if (!customerRepo.findAll().isEmpty()) {
            return mapper.map(customerRepo.findAll(), new TypeToken<List<CustomerDTO>>() {
            }.getType());
        }
        throw new NotFoundException("No Such a Customer");

    }

    @Override
    public String generateCustomerIds() {
        String id = customerRepo.generateCustomerId();
        if (id != null) {
            int tempId = Integer.
                    parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "C00-00" + tempId;
            } else if (tempId <= 99) {
                return "C00-0" + tempId;
            } else {
                return "C00-" + tempId;
            }
        } else {
            return "C00-001";

        }

    }

    @Override
    public int countCustomer() {
        return customerRepo.countCustomer();
    }
}
