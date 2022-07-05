package lk.easy.rental.service.impl;

import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.UserDTO;
import lk.easy.rental.entity.Driver;
import lk.easy.rental.entity.User;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.UserRepo;
import lk.easy.rental.service.UserService;
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
public class UserServiceImpl implements UserService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserRepo userRepo;

    @Override
    public void saveUser(UserDTO userDTO) {
        if (!userRepo.existsById(userDTO.getUserName())){
            userRepo.save(mapper.map(userDTO, User.class));
        }else {
            throw new DuplicateEntryException("User Name Already Exists");
        }
    }

    @Override
    public void deleteUser(String userName) {
        if (userRepo.existsById(userName)){
            userRepo.deleteById(userName);
        }else {
            throw new NotFoundException("Please check the User Name.. No Such User..!");
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getUserName())){
            User map = mapper.map(userDTO, User.class);
            userRepo.save(map);
        }else {
            throw new NotFoundException("No Such User To Update..! Please Check the User Name..!");
        }
    }

    @Override
    public UserDTO searchUser(String userName) {
        if (userRepo.existsById(userName)){
            return mapper.map(userRepo.findById(userName).get(), UserDTO.class);
        }else {
            throw new NotFoundException("No User For " + userName + " ..!");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        if (!userRepo.findAll().isEmpty()){
            return mapper.map(userRepo.findAll(), new TypeToken<List<UserDTO>>(){}.getType());
        }throw new NotFoundException("No Such a user");
    }
}
