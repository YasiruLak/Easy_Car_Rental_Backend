package lk.easy.rental.service.impl;

import lk.easy.rental.dto.VehicleDTO;
import lk.easy.rental.entity.Vehicle;
import lk.easy.rental.enums.AvailabilityType;
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

    @Override
    public String generateVehicleIds() {
        String id = vehicleRepo.generateVehicleId();
        if (id != null) {
            int tempId = Integer.
                    parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "V00-00" + tempId;
            } else if (tempId <= 99) {
                return "V00-0" + tempId;
            } else {
                return "V00-" + tempId;
            }
        } else {
            return "V00-001";
        }
    }

    @Override
    public int brandAndTypeCount(String brand, String type) {
        return vehicleRepo.getAllCountByVehicleTypeAndBrand(brand,type);
    }

    @Override
    public long countVehicle() {
        return vehicleRepo.count();
    }

    @Override
    public void makeVehicleUnavailable(String vehicleId) {
        Vehicle vehicle = vehicleRepo.findById(vehicleId).get();
        vehicle.setVehicleAvailability(AvailabilityType.NOT_AVAILABLE);
        vehicleRepo.save(vehicle);
    }

    @Override
    public void makeVehicleAvailable(String vehicleId) {
        Vehicle vehicle = vehicleRepo.findById(vehicleId).get();
        vehicle.setVehicleAvailability(AvailabilityType.AVAILABLE);
        vehicleRepo.save(vehicle);
    }
}
