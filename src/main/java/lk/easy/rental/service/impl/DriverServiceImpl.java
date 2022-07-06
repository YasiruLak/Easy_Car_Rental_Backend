package lk.easy.rental.service.impl;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.UserDTO;
import lk.easy.rental.entity.Customer;
import lk.easy.rental.entity.Driver;
import lk.easy.rental.entity.User;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.DriverRepo;
import lk.easy.rental.repo.UserRepo;
import lk.easy.rental.service.DriverService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/4/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    DriverRepo driverRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public void saveDriver(DriverDTO driverDTO) {
        if (!driverRepo.existsById(driverDTO.getDriverId())) {
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
    public void deleteDriver(String id) {
        if (driverRepo.existsById(id)){
            driverRepo.deleteById(id);
        }else {
            throw new NotFoundException("Please check the Customer ID.. No Such Customer..!");
        }
    }

    @Override
    public void updateDriver(DriverDTO driverDTO) {
        if (driverRepo.existsById(driverDTO.getDriverId())){
            Driver map = mapper.map(driverDTO, Driver.class);
            driverRepo.save(map);
        }else {
            throw new NotFoundException("No Such Driver To Update..! Please Check the ID..!");
        }
    }

    @Override
    public DriverDTO searchDriver(String id) {
        if (driverRepo.existsById(id)){
            return mapper.map(driverRepo.findById(id).get(), DriverDTO.class);
        }else {
            throw new NotFoundException("No Driver For " + id + " ..!");
        }
    }

    @Override
    public List<DriverDTO> getAllDriver() {
        if (!driverRepo.findAll().isEmpty()){
            return mapper.map(driverRepo.findAll(), new TypeToken<List<DriverDTO>>(){}.getType());
        }throw new NotFoundException("No Such a Driver");
    }
}
