package com.notronix.mws.service;

import com.notronix.mws.AmazonMarketplace;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StandardAmazonServiceFactory implements AmazonServiceFactory
{
    private final Map<AmazonMarketplace, AmazonCredentials> credentialsMap;
    private final Map<AmazonMarketplace, AmazonService> serviceMap = new HashMap<>();

    StandardAmazonServiceFactory(Map<AmazonMarketplace, AmazonCredentials> credentialsMap) {
        this.credentialsMap = Collections.unmodifiableMap(new HashMap<>(credentialsMap));
    }

    @Override
    public AmazonService getService(AmazonMarketplace marketplace) {
        AmazonService service = serviceMap.get(marketplace);

        if (service != null) {
            return service;
        }

        synchronized (serviceMap) {
            service = serviceMap.get(marketplace);
            if (service != null) {
                return service;
            }

            service = createService(marketplace);
            serviceMap.put(marketplace, service);

            return service;
        }
    }

    private AmazonService createService(AmazonMarketplace marketplace) {
        return new StandardAmazonService(marketplace, credentialsMap.get(marketplace));
    }
}
