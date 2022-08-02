package lk.easy.rental.service.impl;

import lk.easy.rental.dto.VehicleDTO;
import lk.easy.rental.embeded.PriceRate;
import lk.easy.rental.entity.Booking;
import lk.easy.rental.entity.BookingDetails;
import lk.easy.rental.entity.Vehicle;
import lk.easy.rental.enums.FuelType;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.BookingRepo;
import lk.easy.rental.repo.VehicleRepo;
import lk.easy.rental.service.BrowseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Autowired
    BookingRepo bookingRepo;

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

    @Override
    public List<VehicleDTO> loadAvailableVehicles(LocalDate pickupDate, LocalDate dropOffDate) {
        LocalDate pickUpDate = pickupDate.minusDays(1);
        LocalDate returnDate = dropOffDate.plusDays(1);
        List<Booking> notAvailableBookingList = bookingRepo.findAllByReturnDateIsAfterAndPickUpDateIsBefore(pickUpDate, returnDate);
        List<Vehicle> notAvailableVehicles = new ArrayList<>();
        List<VehicleDTO> availableVehicles = new ArrayList<>();

        for (Booking booking : notAvailableBookingList) {
            for (BookingDetails bookedVehicle : booking.getBookingDetails()) {
                notAvailableVehicles.add(bookedVehicle.getVehicle());
            }
        }

        L1:for (Vehicle temp : vehicleRepo.findAll()) {
            L2: for (Vehicle notAvailableVehicle : notAvailableVehicles) {
                if (temp.getVehicleId().equals(notAvailableVehicle.getVehicleId())){
                    continue L1;
                }else {
                    continue L2;
                }
            }

            availableVehicles.add(mapper.map(temp,VehicleDTO.class));
        }

        System.out.println("Available"+ availableVehicles.toString());
        System.out.println("Not Available"+ notAvailableVehicles.toString());
//        System.out.println("Not Available Book"+ notAvailableBookingList.toString());

        return availableVehicles;


    }
}
