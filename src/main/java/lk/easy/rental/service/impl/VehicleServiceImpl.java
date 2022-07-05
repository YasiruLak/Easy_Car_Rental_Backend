package lk.easy.rental.service.impl;

import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.VehicleDTO;
import lk.easy.rental.entity.Driver;
import lk.easy.rental.entity.Vehicle;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.VehicleRepo;
import lk.easy.rental.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    VehicleRepo vehicleRepo;

    @Override
    public void saveVehicle(VehicleDTO vehicleDTO) {
        if (!vehicleRepo.existsById(vehicleDTO.getVehicleId())){
            vehicleRepo.save(mapper.map(vehicleDTO, Vehicle.class));
        }else {
            throw new DuplicateEntryException("Vehicle Already Exists");
        }
    }

    @Override
    public void deleteVehicle(String id) {
        if (vehicleRepo.existsById(id)){
            vehicleRepo.deleteById(id);
        }else {
            throw new NotFoundException("Please check the Vehicle ID.. No Such Vehicle..!");
        }
    }

    @Override
    public void updateVehicle(VehicleDTO vehicleDTO) {
        if (vehicleRepo.existsById(vehicleDTO.getVehicleId())){
            Vehicle map = mapper.map(vehicleDTO, Vehicle.class);
            vehicleRepo.save(map);
        }else {
            throw new NotFoundException("No Such Vehicle To Update..! Please Check the ID..!");
        }
    }

    @Override
    public VehicleDTO searchVehicle(String id) {
        if (vehicleRepo.existsById(id)){
            return mapper.map(vehicleRepo.findById(id).get(), VehicleDTO.class);
        }else {
            throw new NotFoundException("No Vehicle For " + id + " ..!");
        }
    }

    @Override
    public List<VehicleDTO> getAllVehicle() {
        if (!vehicleRepo.findAll().isEmpty()){
            return mapper.map(vehicleRepo.findAll(), new TypeToken<List<VehicleDTO>>(){}.getType());
        }throw new NotFoundException("No Such a Vehicle");
    }
}