package com.clicknbuy.clicknbuyecommerce.exceptions;

public class DataException extends GenericException {
    public DataException(String msg) {
        super(msg);
    }

    public DataException(Throwable cause) {
        super(cause);
    }

    public DataException(Throwable cause, String message) {
        super(cause, message);
    }

    public DataException(String message, Object... details) {
        super(message, details);
    }

    public DataException(Throwable cause, String message, Object... details) {
        super(cause, message, details);
    }
}