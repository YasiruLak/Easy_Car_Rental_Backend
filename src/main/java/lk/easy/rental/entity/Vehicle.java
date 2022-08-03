package lk.easy.rental.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

import javax.persistence.*;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vehicle {

    @Id
    private String vehicleId;
    @Column(name = "registration_no")
    private String registrationNo;
    @Column(name = "brand")
    private String vehicleBrand;
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;
    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type")
    private FuelType fuelType;
    @Column(name = "no_of_passenger")
    private int numberOfPassenger;
    @Column(name = "colour")
    private String vehicleColour;
    @Column(name = "transmission_type")
    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;
    @Column(name = "refundable_damage_fee")
    private double refundableDamagedFee;
    @Column(name = "vehicle_service_mileage")
    private int vehicleMileage;
    @Embedded
    @Column(name = "price_rate")
    private PriceRate vehiclePriceRate;
    @Embedded
    @Column(name = "free_mileage")
    private Mileage freeMileage;
    @Column(name = "last_service_mileage")
    private int lastServiceMileage;
    private double extraKmPer;
    @Enumerated(EnumType.STRING)
    @Column(name = "availability")
    private AvailabilityType vehicleAvailability;
}
