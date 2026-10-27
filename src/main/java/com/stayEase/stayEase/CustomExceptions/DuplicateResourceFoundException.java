package com.stayEase.stayEase.CustomExceptions;

public class DuplicateResourceFoundException extends RuntimeException {

    public DuplicateResourceFoundException (String message)
    {
        super(message);
    }
}
