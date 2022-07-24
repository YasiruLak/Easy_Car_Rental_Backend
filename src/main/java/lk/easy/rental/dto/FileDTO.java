package lk.easy.rental.dto;

import lk.easy.rental.enums.ReferencedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/24/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FileDTO {

    private String referencedId;
    private MultipartFile uploadFile;
    private ReferencedType fileCategory;
}
