package lk.easy.rental.dto;

import lk.easy.rental.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
public class BookingDTO {

    private String bookingId;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private LocalDate returnDate;
    private RequestType DriverRequestType;
    private CustomerDTO customer;
    private List<BookingDetailsDTO> bookingDetails;
    private List<DriverScheduleDTO> driverSchedules;
}
