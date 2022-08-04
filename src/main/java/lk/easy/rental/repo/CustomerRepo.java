package lk.easy.rental.repo;

import lk.easy.rental.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/3/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface CustomerRepo extends JpaRepository<Customer,String> {

    @Query(value = "SELECT COUNT(*) FROM Customer", nativeQuery = true)
    int countCustomer();

    @Query(value = "SELECT id FROM Customer ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String generateCustomerId();

//    int countById();

}
