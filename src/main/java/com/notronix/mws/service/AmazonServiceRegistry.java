package com.notronix.mws.service;

public interface AmazonServiceRegistry
{
    AmazonServiceFactory buildServiceFactory(AmazonConfiguration configuration) throws NullPointerException;
}
