package lk.easy.rental.service;

import lk.easy.rental.dto.DriverDTO;
import lk.easy.rental.dto.PaymentDTO;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/8/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public interface PaymentService {

    void savePayment(PaymentDTO paymentDTO);
    void deletePayment(String id);
    void updatePayment(PaymentDTO paymentDTO);
    PaymentDTO searchPayment(String id);
    List<PaymentDTO> getAllPayment();
}
