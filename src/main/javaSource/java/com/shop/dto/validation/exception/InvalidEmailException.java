package com.shop.dto.validation.exception;

/**
 * Created by ostap on 3/11/17.
 */
public class InvalidEmailException extends Throwable {
    public InvalidEmailException(final String error){
        super(error);
    }
}
