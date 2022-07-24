package lk.easy.rental.service;

import lk.easy.rental.dto.FileDTO;
import lk.easy.rental.enums.ReferencedType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/24/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface FileService {

    ResponseEntity<?> store(FileDTO fileDTO) throws IOException, Exception;

    byte[] getFile(String referencedId, ReferencedType referencedType) throws IOException;
}
