package com.notronix.mws.service;

public class StandardAmazonServiceRegistry implements AmazonServiceRegistry
{
    @Override
    public AmazonServiceFactory buildServiceFactory(AmazonConfiguration configuration) throws NullPointerException {
        if (configuration == null) {
            throw new NullPointerException("null configuration");
        }

        return new StandardAmazonServiceFactory(configuration.configure().getCredentialsMap());
    }
}
