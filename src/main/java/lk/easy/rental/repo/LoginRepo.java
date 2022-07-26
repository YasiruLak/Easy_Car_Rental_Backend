package lk.easy.rental.repo;

import lk.easy.rental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/23/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface LoginRepo extends JpaRepository<User,String> {
}