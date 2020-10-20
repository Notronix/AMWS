package com.notronix.mws.api.service;

public class ThrottlingException extends AmazonAPIException
{
    public ThrottlingException(String message) {
        super(message);
    }

    public ThrottlingException(String message, Throwable cause) {
        super(message, cause);
    }
}
