package com.notronix.mws.service;

import static java.util.Objects.requireNonNull;

public final class StandardAmazonCredentials implements AmazonCredentials
{
    private final String merchantId;
    private final String accessKey;
    private final String secretKey;

    public StandardAmazonCredentials(String merchantId, String accessKey, String secretKey) {
        this.merchantId = requireNonNull(merchantId);
        this.accessKey = requireNonNull(accessKey);
        this.secretKey = requireNonNull(secretKey);
    }

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
}
