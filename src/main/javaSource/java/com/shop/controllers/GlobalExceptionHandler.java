package com.shop.controllers;

import com.shop.dto.validation.exception.EmailExistsException;
import com.shop.dto.validation.exception.InvalidEmailException;
import com.shop.dto.validation.exception.InvalidProductName;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.io.IOException;

/**
 * Created by ostap on 4/12/17.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="IOException occured")
    public void handleIOException(){
        //returning 404 error code
    }

    @ExceptionHandler(InvalidEmailException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="InvalidEmailException occured")
    public void handleInvalidEmailException(){
        //returning 404 error code
    }

    @ExceptionHandler(InvalidProductName.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="InvalidProductNameException occured")
    public void handleInvalidProductName(){
        //returning 404 error code
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="EmailExistsException occured")
    public void handleEmailExistsException(){
        //returning 404 error code
    }

}
