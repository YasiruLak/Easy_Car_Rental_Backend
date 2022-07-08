package lk.easy.rental.service.impl;

import lk.easy.rental.dto.BookingDTO;
import lk.easy.rental.dto.PaymentDTO;
import lk.easy.rental.entity.Booking;
import lk.easy.rental.entity.Payment;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.PaymentRepo;
import lk.easy.rental.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/8/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    PaymentRepo paymentRepo;

    @Override
    public void savePayment(PaymentDTO paymentDTO) {
        if (!paymentRepo.existsById(paymentDTO.getPaymentId())){
            paymentRepo.save(mapper.map(paymentDTO, Payment.class));
        }else {
            throw new DuplicateEntryException("Already Done");
        }
    }

    @Override
    public void deletePayment(String id) {
        if(paymentRepo.existsById(id)){
            paymentRepo.deleteById(id);
        }else{
            throw new NotFoundException("Please check the Payment ID.. No Such a Payment..!");
        }
    }

    @Override
    public void updatePayment(PaymentDTO paymentDTO) {
        if (paymentRepo.existsById(paymentDTO.getPaymentId())){
            Payment map = mapper.map(paymentDTO, Payment.class);
            paymentRepo.save(map);
        }else{
            throw new NotFoundException("No Such Payment To Update..! Please Check the ID..!");
        }
    }

    @Override
    public PaymentDTO searchPayment(String id) {
        if (paymentRepo.existsById(id)){
            return mapper.map(paymentRepo.findById(id).get(),PaymentDTO.class);
        }else{
            throw new NotFoundException("No Payment For " + id + " ..!");
        }
    }

    @Override
    public List<PaymentDTO> getAllPayment() {
        if (!paymentRepo.findAll().isEmpty()){
            return mapper.map(paymentRepo.findAll(), new TypeToken<List<PaymentDTO>>(){}.getType());
        }throw new NotFoundException("No Such a Payment");
    }
}
