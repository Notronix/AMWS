package com.notronix.mws.api.service;

public class DeniedAccessException extends AmazonAPIException
{
    public DeniedAccessException(String message) {
        super(message);
    }

    public DeniedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
