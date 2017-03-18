package com.shop.dto.validation.exception;

/**
 * Created by ostap on 2/28/17.
 */
public class EmailExistsException extends Throwable {

    public EmailExistsException(final String message) {
        super(message);
    }

}
