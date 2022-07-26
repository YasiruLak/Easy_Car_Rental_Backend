package lk.easy.rental.service.impl;

import lk.easy.rental.dto.BookingDTO;
import lk.easy.rental.dto.BookingDetailsDTO;
import lk.easy.rental.entity.Booking;
import lk.easy.rental.exception.DuplicateEntryException;
import lk.easy.rental.exception.NotFoundException;
import lk.easy.rental.repo.BookingDetailRepo;
import lk.easy.rental.repo.BookingRepo;
import lk.easy.rental.repo.CustomerRepo;
import lk.easy.rental.service.BookingService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/5/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    BookingRepo bookingRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    BookingDetailRepo bookingDetailRepo;

    @Override
    public void saveBooking(BookingDTO bookingDTO) {
        if (!bookingRepo.existsById(bookingDTO.getBookingId())) {
            if (!customerRepo.existsById(bookingDTO.getCustomer().getId())) {
                if (!bookingDTO.getBookingDetails().isEmpty()) {
                    if (!bookingDTO.getDriverRequestType().equals("YES")) {
                        if (!bookingDTO.getDriverSchedules().isEmpty()){
                            bookingRepo.save(mapper.map(bookingDTO, Booking.class));
                        }
                        //-----------------------------------------------------
                        //------------if driver isn't request------------------
                    }else {
                        if (bookingDTO.getDriverSchedules().isEmpty()){
                            bookingRepo.save(mapper.map(bookingDTO, Booking.class));
                        }
                    }
                    //-----------------------------------------------------
                }else {
                    throw new DuplicateEntryException("No vehicle added for the booking");
                }
            }else {
                throw new DuplicateEntryException("No such a customer..!");
            }
        }else {
            throw new DuplicateEntryException("Booking already exist");
        }
    }

            @Override
            public void deleteBooking (String id){
                if (bookingRepo.existsById(id)) {
                    bookingRepo.deleteById(id);
                } else {
                    throw new NotFoundException("Please check the Booking ID.. No Such a Booking..!");
                }
            }

            @Override
            public void updateBooking (BookingDTO bookingDTO){
                if (bookingRepo.existsById(bookingDTO.getBookingId())) {
                    Booking map = mapper.map(bookingDTO, Booking.class);
                    bookingRepo.save(map);
                } else {
                    throw new NotFoundException("No Such Booking To Update..! Please Check the ID..!");
                }
            }

            @Override
            public BookingDTO searchBooking (String id){
                if (bookingRepo.existsById(id)) {
                    return mapper.map(bookingRepo.findById(id).get(), BookingDTO.class);
                } else {
                    throw new NotFoundException("No Booking For " + id + " ..!");
                }
            }

            @Override
            public List<BookingDTO> getAllBooking () {
                if (!bookingRepo.findAll().isEmpty()) {
                    return mapper.map(bookingRepo.findAll(), new TypeToken<List<BookingDTO>>() {
                    }.getType());
                }
                throw new NotFoundException("No Such a booking");
            }

            public boolean isVehicleAvailable (BookingDTO dto){
                LocalDate pickUpDate = dto.getPickUpDate().minusDays(1);
                LocalDate returnDate = dto.getReturnDate().plusDays(1);

                List<Booking> allByPickupDateAndReturnDate = bookingRepo.findAllByPickUpDateAndReturnDate(pickUpDate, returnDate);

                //No Bookings For Any Vehicle
                if (!allByPickupDateAndReturnDate.isEmpty()) {

                    //If not
                    for (Booking booking : allByPickupDateAndReturnDate) {
                        for (BookingDetailsDTO tempBookingDetail : dto.getBookingDetails()) {
                            if (bookingDetailRepo.existsByBookingIdAndVehicleId(booking.getBookingId(), tempBookingDetail.getVehicleId())) {
                                return false;
                            }
                        }
                    }
                }
                return true;
            }
        }
