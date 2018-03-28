package com.notronix.mws;

public abstract class AmazonAPIException extends Exception
{
    public AmazonAPIException(String message)
    {
        super(message);
    }

    public AmazonAPIException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
