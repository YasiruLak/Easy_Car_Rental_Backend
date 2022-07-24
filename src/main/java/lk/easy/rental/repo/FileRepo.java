package lk.easy.rental.repo;

import lk.easy.rental.entity.File;
import lk.easy.rental.enums.ReferencedType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/25/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface FileRepo extends JpaRepository<File,String> {

    boolean existsByReferencedIdAndReferencedType(String referencedId, ReferencedType referencedType);

    File findByReferencedIdAndReferencedType(String referencedId, ReferencedType referencedType);
}
