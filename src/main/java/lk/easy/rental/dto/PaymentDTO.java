package lk.easy.rental.dto;

import lk.easy.rental.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

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
public class PaymentDTO {

    private String paymentId;
    private LocalDate paymentDate;
    private String invoiceNo;
    private double amount;
    private PaymentType paymentType;
    private BookingDTO booking;
}
