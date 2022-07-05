package lk.easy.rental.entity;

import lk.easy.rental.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/4/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class Payment {

    @Id
    @Column(name = "payment_id")
    private String paymentId;
    @Column(name = "booking_id")
    private String bookingId;
    @Column(name = "date")
    private LocalDate paymentDate;
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Column(name = "amount")
    private double amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

}
