package lk.easy.rental.repo;

import lk.easy.rental.entity.Admin;
import lk.easy.rental.entity.Customer;
import lk.easy.rental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface AdminRepo extends JpaRepository<Admin,String> {

    @Query(value = "SELECT admin_id FROM admin ORDER BY admin_id DESC LIMIT 1", nativeQuery = true)
    String generateAdminId();

    Admin findByUser(User byId);
}
