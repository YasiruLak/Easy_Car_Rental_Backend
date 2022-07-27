package lk.easy.rental.repo;

import lk.easy.rental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUserName(String username);

    boolean existsByUserName(String name);

    Optional<User> findByUserId(int userId);

    User findByUserNameAndPassword(String userName, String password);


}
