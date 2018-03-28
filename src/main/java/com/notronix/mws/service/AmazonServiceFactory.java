package com.notronix.mws.service;

import com.notronix.mws.AmazonMarketplace;

public interface AmazonServiceFactory
{
    AmazonService getService(AmazonMarketplace marketplace);
}
