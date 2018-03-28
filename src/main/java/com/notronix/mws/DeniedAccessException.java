package com.notronix.mws;

public class DeniedAccessException extends AmazonAPIException
{
    public DeniedAccessException(String message) {
        super(message);
    }

    public DeniedAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
