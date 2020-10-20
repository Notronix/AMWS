package com.notronix.mws.api.service;

public interface AmazonCredentials
{
    String getMerchantId();
    String getAccessKey();
    String getSecretKey();

    static AmazonCredentials with(String merchantId, String accessKey, String secretKey) {
        return new AmazonCredentials()
        {
            @Override
            public String getMerchantId() {
                return merchantId;
            }

            @Override
            public String getAccessKey() {
                return accessKey;
            }

            @Override
            public String getSecretKey() {
                return secretKey;
            }
        };
    }
}
