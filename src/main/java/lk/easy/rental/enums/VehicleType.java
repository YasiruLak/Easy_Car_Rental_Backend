package lk.easy.rental.enums;

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
@ToString
public enum VehicleType {

    GENERAL,
    LUXURY,
    PREMIUM
}
