package lk.easy.rental.entity;

import lk.easy.rental.enums.ReferencedType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

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
@Entity
public class File {

    @Id
    @GeneratedValue
    private int id;
    private String referencedId;
    @Enumerated(EnumType.STRING)
    private ReferencedType referencedType;
    private String filePath;

    public File(String referencedId, ReferencedType referencedType, String filePath) {
        this.referencedId = referencedId;
        this.referencedType = referencedType;
        this.filePath = filePath;
    }
}
