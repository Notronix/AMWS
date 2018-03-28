package com.notronix.mws.service;

import com.notronix.mws.AmazonMarketplace;

public interface AmazonCredentials
{
    AmazonMarketplace getMarketplace();

    boolean isEmpty();

    String getMerchantId();

    String getAccessKey();

    String getSecretKey();

    String getApplicationName();

    String getApplicationVersion();
}
