package com.clicknbuy.clicknbuyecommerce.exceptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericException extends RuntimeException {
    private final transient List<Object> errorDetail;

    public GenericException(String message) {
        super(message);
        errorDetail = Collections.emptyList();
    }

    public GenericException(Throwable cause) {
        super(cause);
        errorDetail = Collections.emptyList();
    }

    public GenericException(Throwable cause, String message) {
        super(message, cause);
        errorDetail = Collections.emptyList();
    }

    public GenericException(String message, Object... details) {
        super(message);
        this.errorDetail = Arrays.asList(details);
    }

    public GenericException(Throwable cause, String message, Object... details) {
        super(message, cause);
        this.errorDetail = Arrays.asList(details);
    }

    public List<Object> getErrorDetail() {
        return errorDetail;
    }
}