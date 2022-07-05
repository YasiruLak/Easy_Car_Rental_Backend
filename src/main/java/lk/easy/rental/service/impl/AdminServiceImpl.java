package lk.easy.rental.service.impl;

import lk.easy.rental.dto.AdminDTO;
import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.entity.Admin;
import lk.easy.rental.entity.Driver;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.AdminRepo;
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

    @Override
    public void saveAdmin(AdminDTO adminDTO) {
        if (!adminRepo.existsById(adminDTO.getAdminId())){
            adminRepo.save(mapper.map(adminDTO, Admin.class));
        }else {
            throw new DuplicateEntryException("Driver Already Exists");
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
}