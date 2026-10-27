package com.stayEase.stayEase.CustomExceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException (String message)
    {

//  super(message) calls the parent class (RuntimeException) constructor and passes it the message.
        super(message);
    }

}
