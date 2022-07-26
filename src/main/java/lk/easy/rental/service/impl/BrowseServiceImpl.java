package lk.easy.rental.service.impl;

import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.VehicleDTO;
import lk.easy.rental.embeded.PriceRate;
import lk.easy.rental.enums.FuelType;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.VehicleRepo;
import lk.easy.rental.service.BrowseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
public class BrowseServiceImpl implements BrowseService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    VehicleRepo vehicleRepo;

    @Override
    public List<VehicleDTO> getAllNoOfPassenger(int noOfPassenger) {
        if (!vehicleRepo.findAllByNumberOfPassenger(noOfPassenger).isEmpty()) {
            return mapper.map(vehicleRepo.findAllByNumberOfPassenger(noOfPassenger), new TypeToken<List<VehicleDTO>>() {
            }.getType());
        }
        throw new NotFoundException("No Such a Result");
    }

    @Override
    public List<VehicleDTO> getAllFuelType(FuelType fuelType) {
        if (!vehicleRepo.findAllByFuelType(fuelType).isEmpty()) {
            return mapper.map(vehicleRepo.findAllByFuelType(fuelType), new TypeToken<List<VehicleDTO>>() {
            }.getType());
        }
        throw new NotFoundException("No Such a Result");
    }

    @Override
    public List<VehicleDTO> getPriceRate(PriceRate priceRate) {
        if (!vehicleRepo.findAllByVehiclePriceRate(priceRate).isEmpty()) {
            return mapper.map(vehicleRepo.findAllByVehiclePriceRate(priceRate), new TypeToken<List<VehicleDTO>>() {
            }.getType());
        }
        throw new NotFoundException("No Such a Result");
    }
}
