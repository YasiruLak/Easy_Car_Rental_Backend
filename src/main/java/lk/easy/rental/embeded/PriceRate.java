package lk.easy.rental.embeded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/3/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PriceRate {

    private double dailyRate;
    private double monthlyRate;
}
