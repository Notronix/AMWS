package com.notronix.mws;

public class GeneralAmazonAPIException extends AmazonAPIException
{
    public GeneralAmazonAPIException(String message) {
        super(message);
    }

    public GeneralAmazonAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
