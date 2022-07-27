package lk.easy.rental.service.impl;

import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.UserDTO;
import lk.easy.rental.entity.User;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.UserRepo;
import lk.easy.rental.service.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/28/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper mapper;

    @Override
    public UserDTO loginUser(String userName, String password) {
        User byUserName = userRepo.findByUserName(userName);
        if (byUserName != null){
            if (byUserName.getPassword().equals(password)){
                return mapper.map(byUserName,UserDTO.class);
            }
            throw new NotFoundException("Password Incorrect");
        }
        throw new NotFoundException("User name Incorrect");
    }
}
