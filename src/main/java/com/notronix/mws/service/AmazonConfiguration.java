package com.notronix.mws.service;

import com.notronix.mws.AmazonMarketplace;

import java.util.Map;

public interface AmazonConfiguration
{
    AmazonConfiguration configure();

    Map<AmazonMarketplace, AmazonCredentials> getCredentialsMap();
}
