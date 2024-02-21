package com.clicknbuy.clicknbuyecommerce.exceptions;

public class NoResultsFoundException extends GenericException {

    public NoResultsFoundException(String message) { super(message); } 

    public NoResultsFoundException(Throwable cause) { super(cause); } 

    public NoResultsFoundException(Throwable cause, String message) { super(cause, message); } 

    public NoResultsFoundException(String message, Object... details) { super(message, details); } 

    public NoResultsFoundException(Throwable cause, String message, Object... details) { 
        super(cause, message, details); }
}


