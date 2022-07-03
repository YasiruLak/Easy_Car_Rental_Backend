package lk.easy.rental.entity;

import lk.easy.rental.embeded.Mileage;
import lk.easy.rental.embeded.PriceRate;
import lk.easy.rental.enums.AvailabilityType;
import lk.easy.rental.enums.FuelType;
import lk.easy.rental.enums.TransmissionType;
import lk.easy.rental.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/3/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Vehicle {

    @Id
    private String vehicleId;
    private String registrationNo;
    private String vehicleBrand;
    private VehicleType vehicleType;
    private FuelType fuelType;
    private int numberOfPassenger;
    private String vehicleColour;
    private TransmissionType transmissionType;
    private double refundableDamagedFee;
    @Embedded
    private Mileage vehicleMileage;
    @Embedded
    private PriceRate vehiclePriceRate;
    private AvailabilityType vehicleAvailability;
}
