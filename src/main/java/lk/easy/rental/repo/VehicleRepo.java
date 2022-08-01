package lk.easy.rental.repo;

import lk.easy.rental.embeded.PriceRate;
import lk.easy.rental.entity.Vehicle;
import lk.easy.rental.enums.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface VehicleRepo extends JpaRepository<Vehicle,String> {

    List<Vehicle>findAllByNumberOfPassenger(int noOfPassenger);

    List<Vehicle>findAllByFuelType(FuelType fuelType);

    List<Vehicle>findAllByVehiclePriceRate(PriceRate priceRate);

    @Query(value = "SELECT vehicleId FROM vehicle ORDER BY vehicleId DESC LIMIT 1", nativeQuery = true)
    String generateVehicleId();
}
