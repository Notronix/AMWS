package com.notronix.mws.api;

public class DeniedAccessException extends AmazonAPIException
{
    public DeniedAccessException(String message) {
        super(message);
    }

    public DeniedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
