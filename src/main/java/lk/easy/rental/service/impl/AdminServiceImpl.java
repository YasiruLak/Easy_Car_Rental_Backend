package lk.easy.rental.service.impl;

import lk.easy.rental.dto.AdminDTO;
import lk.easy.rental.dto.CustomerDTO;
import lk.easy.rental.entity.*;
import lk.easy.rental.enums.RequestType;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.*;
import lk.easy.rental.service.AdminService;
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
public class AdminServiceImpl implements AdminService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    AdminRepo adminRepo;

    @Autowired
    UserRequestRepo userRequestRepo;

    @Autowired
    CustomerRequestRepo customerRequestRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    VehicleRepo vehicleRepo;

    @Override
    public void saveAdmin(AdminDTO adminDTO) {
        if (!adminRepo.existsById(adminDTO.getAdminId())){
            if (!userRepo.existsByUserName(adminDTO.getUser().getUserName())) {
                adminRepo.save(mapper.map(adminDTO, Admin.class));
            }else {
                throw new DuplicateEntryException("User Name Already Exists");
            }
        }else{
            throw new DuplicateEntryException("Admin Already Exists");
        }
    }

    @Override
    public void deleteAdmin(String id) {
        if (adminRepo.existsById(id)){
            adminRepo.deleteById(id);
        }else {
            throw new NotFoundException("Please check the Admin ID.. No Such Admin..!");
        }
    }

    @Override
    public void updateAdmin(AdminDTO adminDTO) {
        if (adminRepo.existsById(adminDTO.getAdminId())){
            Admin map = mapper.map(adminDTO, Admin.class);
            adminRepo.save(map);
        }else {
            throw new NotFoundException("No Such Admin To Update..! Please Check the ID..!");
        }
    }

    @Override
    public AdminDTO searchAdmin(String id) {
        if (adminRepo.existsById(id)){
            return mapper.map(adminRepo.findById(id).get(), AdminDTO.class);
        }else {
            throw new NotFoundException("No Admin For " + id + " ..!");
        }
    }

    @Override
    public List<AdminDTO> getAllAdmin() {
        if (!adminRepo.findAll().isEmpty()){
            return mapper.map(adminRepo.findAll(), new TypeToken<List<AdminDTO>>(){}.getType());
        }throw new NotFoundException("No Such a Admin");
    }

    @Override
    public String generateAdminIds() {
        String id = adminRepo.generateAdminId();
        if (id != null) {
            int tempId = Integer.
                    parseInt(id.split("-")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "A00-00" + tempId;
            } else if (tempId <= 99) {
                return "A00-0" + tempId;
            } else {
                return "A00-" + tempId;
            }
        } else {
            return "A00-001";
        }
    }

    @Override
    public long countAdmin() {
        return adminRepo.count();
    }

    @Override
    public AdminDTO getAdminInUserName(String userName) {
        if (userRepo.existsByUserName(userName)) {
            User byId = userRepo.findByUserName(userName);
            return mapper.map(adminRepo.findByUser(byId), AdminDTO.class);
        }else {
            throw new NotFoundException("Admin Not Found");

        }
    }

    @Override
    public void acceptCustomer(CustomerDTO dto) {
        CustomerRequest customerRequest = customerRequestRepo.findById(dto.getId()).get();
        User user = mapper.map(customerRequest.getUser(), User.class);
        Customer customer = mapper.map(dto, Customer.class);
        customer.setUser(user);

        customerRepo.save(customer);
        customerRequestRepo.deleteById(dto.getId());
    }

    @Override
    public void denyCustomer(String denyCustomer) {
        customerRequestRepo.deleteById(denyCustomer);
    }

    @Override
    public void notifyMaintenance() {
        List<Vehicle> allVehicles = vehicleRepo.findAll();

        for (Vehicle vehicle : allVehicles) {

            int lastServiceMileage = vehicle.getLastServiceMileage();
            int mileage = vehicle.getVehicleMileage();

            if (mileage >= (lastServiceMileage+5000)){
                vehicle.setNeedMaintenance(RequestType.YES);
            }else {
                vehicle.setNeedMaintenance(RequestType.NO);
            }
            vehicleRepo.save(vehicle);
        }
    }

//    @Override
//    public void acceptBookingRequest(String id) {
//        Booking bookingRequest = bookingRepo.findById(id).get();
//        bookingRequest.setStatus(BookingStatus.ACCEPTED);
//        bookingRepo.save(bookingRequest);
//    }
//
//    @Override
//    public void denyBookingRequest(String id, String reason) {
//        bookingRepo.findById(id).get();
//        bookingRequest.setStatus(BookingStatus.DENIED);
//        bookingRequest.setDeniedReason(reason);
//        bookingRepo.save(bookingRequest);
//    }
}
