package lk.easy.rental.service.impl;

import lk.easy.rental.dto.UserDTO;
import lk.easy.rental.entity.User;
import lk.easy.rental.enums.Role;
import lk.easy.rental.jwt.AuthenticationRequest;
import lk.easy.rental.jwt.JwtGenerator;
import lk.easy.rental.repo.UserRepo;
import lk.easy.rental.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/12/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper mapper;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<?> logInUser(AuthenticationRequest authenticationRequest) {
        try {
            if (authenticationRequest.equals(null)) {
                return new ResponseEntity<>("Username & Password not found", HttpStatus.BAD_REQUEST);
            }
            User user = userRepo.findByUserName(authenticationRequest.getUsername());
            if (user.equals(null)) {
                return new ResponseEntity<>("User name doesn't match", HttpStatus.UNAUTHORIZED);
            }
            if (passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
                String token = generateToken(user);
                if (token.equals(null)) {
                    return new ResponseEntity<>("Token not generated", HttpStatus.UNAUTHORIZED);
                }
                UserDTO userDTO = new UserDTO(
                        token,
                        user.getUserId(),
                        user.getUserName()

                );
                return new ResponseEntity<>(userDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>("Invalid user credential", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String generateToken(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + Role.ADMIN.toString()));
        return JwtGenerator.generateToken(user.getUserName(), String.valueOf(user.getUserId()), authorities);
    }
}
