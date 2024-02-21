package com.clicknbuy.clicknbuyecommerce.exceptions;

import com.clicknbuy.clicknbuyecommerce.exceptions.GenericException;

public class BadRequestException extends GenericException {
    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(Throwable cause, String message) {
        super(cause, message);
    }

    public BadRequestException(String message, Object... details) {
        super(message, details);
    }

    public BadRequestException(Throwable cause, String message, Object... details) {
        super(cause, message, details);
    }
}