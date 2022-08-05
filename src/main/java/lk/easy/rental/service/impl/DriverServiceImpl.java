package lk.easy.rental.service.impl;

import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.entity.Booking;
import lk.easy.rental.entity.Driver;
import lk.easy.rental.entity.DriverSchedule;
import lk.easy.rental.entity.User;
import lk.easy.rental.enums.AvailabilityType;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.BookingRepo;
import lk.easy.rental.repo.DriverRepo;
import lk.easy.rental.repo.UserRepo;
import lk.easy.rental.service.DriverService;
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

    @Autowired
    BookingRepo bookingRepo;

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
    public void deleteDriver(String id) {
        if (driverRepo.existsById(id)){
            driverRepo.deleteById(id);
        }else {
            throw new NotFoundException("Please check the Driver ID.. No Such Driver..!");
        }
    }

    @Override
    public void updateDriver(DriverDTO driverDTO) {
        if (driverRepo.existsById(driverDTO.getId())){
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

    @Override
    public String generateDriverIds() {
        String id = driverRepo.generateDriverId();
        if (id != null) {
            int tempId = Integer.
                    parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "D00-00" + tempId;
            } else if (tempId <= 99) {
                return "D00-0" + tempId;
            } else {
                return "D00-" + tempId;
            }
        } else {
            return "D00-001";

        }
    }

    @Override
    public long countDrivers() {
        return driverRepo.count();
    }

    @Override
    public DriverDTO getDriverInUserName(String userName) {
        if (userRepo.existsByUserName(userName)) {
            User byId = userRepo.findByUserName(userName);
            return mapper.map(driverRepo.findByUser(byId), DriverDTO.class);
        }else {
            throw new NotFoundException("Driver Not Found");

        }
    }

    @Override
    public DriverDTO loadAvailableDriver(LocalDate pickupDate, LocalDate dropOffDate) {
        LocalDate pickUpDate = pickupDate.minusDays(1);
        LocalDate returnDate = dropOffDate.plusDays(1);
        List<Booking> notAvailableBookingList = bookingRepo.findAllByReturnDateIsAfterAndPickUpDateIsBefore(pickUpDate, returnDate);
        List<Driver> notAvailableDrivers = new ArrayList<>();

        for (Booking booking : notAvailableBookingList) {
            for (DriverSchedule bookedDriver : booking.getDriverSchedules()) {
                notAvailableDrivers.add(bookedDriver.getDriver());
            }
        }

        L1:
        for (Driver temp : driverRepo.findAll()) {
            L2:
            for (Driver notAvailableDriver : notAvailableDrivers) {
                if (temp.getId().equals(notAvailableDriver.getId())) {
                    continue L1;
                } else {
                    continue L2;
                }
            }

            return mapper.map(temp, DriverDTO.class);

        }
        throw new RuntimeException("No Driver Available");
    }

    @Override
    public DriverDTO getAvailableDriver() {
        Driver availableDriver = driverRepo.findFirstByDriverAvailability(AvailabilityType.AVAILABLE);

        return mapper.map(availableDriver,DriverDTO.class);
    }

}
