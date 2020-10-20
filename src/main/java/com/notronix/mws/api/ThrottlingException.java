package com.notronix.mws.api;

public class ThrottlingException extends AmazonAPIException
{
    public ThrottlingException(String message) {
        super(message);
    }

    public ThrottlingException(String message, Throwable cause) {
        super(message, cause);
    }
}
