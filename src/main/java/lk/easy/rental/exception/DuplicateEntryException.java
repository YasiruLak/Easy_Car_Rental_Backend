package lk.easy.rental.exception;

/**
 * @author : Yasiru Dahanayaka
 * @name : Car_Rental_Backend
 * @date : 7/4/2022
 * @month : 07
 * @year : 2022
 * @since : 0.1.0
 **/
public class DuplicateEntryException extends RuntimeException{

    public DuplicateEntryException(String message) {
        super(message);
    }
}
