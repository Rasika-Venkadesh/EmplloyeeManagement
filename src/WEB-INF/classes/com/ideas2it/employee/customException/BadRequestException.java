package com.ideas2it.employee.customException;
import java.util.List;
import java.util.ArrayList;


/**
 * <p>
 * BadRequestException class is to handle to exceptions, when Employee details 
 * are invalid.
 * </p> 
 *
 * @author Rasika Venkadesh
 * @version 1.0
 **/
public class BadRequestException extends RuntimeException {
    public List<Integer> errors = new ArrayList<>();

    public BadRequestException(List<Integer> errors, String message) {
        super(message);
        this.errors = errors;
    }

    public BadRequestException(String message) {
        super(message);
    }
}