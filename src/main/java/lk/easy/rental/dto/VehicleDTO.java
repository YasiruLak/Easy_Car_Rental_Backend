package lk.easy.rental.dto;

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


/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class VehicleDTO {

    private String vehicleId;
    private String registrationNo;
    private String vehicleBrand;
    private VehicleType vehicleType;
    private FuelType fuelType;
    private int numberOfPassenger;
    private String vehicleColour;
    private TransmissionType transmissionType;
    private double refundableDamagedFee;
    private Mileage vehicleMileage;
    private PriceRate vehiclePriceRate;
    private String freeMileage;
    private String lastServiceMileage;
    private AvailabilityType vehicleAvailability;
}
