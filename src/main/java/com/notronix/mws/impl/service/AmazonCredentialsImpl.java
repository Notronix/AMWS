package com.notronix.mws.impl.service;

import com.notronix.mws.api.service.AmazonCredentials;

import java.util.Objects;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

public class AmazonCredentialsImpl implements AmazonCredentials
{
    private String merchantId;
    private String accessKey;
    private String secretKey;

    public AmazonCredentialsImpl(String merchantId, String accessKey, String secretKey) {
        this.merchantId = requireNonNull(merchantId);
        this.accessKey = requireNonNull(accessKey);
        this.secretKey = requireNonNull(secretKey);
    }

    @Override
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Override
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public int hashCode() {
        return 913
                + (nonNull(merchantId) ? merchantId.hashCode() : 0)
                + (nonNull(accessKey) ? accessKey.hashCode() : 0)
                + (nonNull(secretKey) ? secretKey.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AmazonCredentials
                && Objects.equals(((AmazonCredentials) obj).getMerchantId(), merchantId)
                && Objects.equals(((AmazonCredentials) obj).getAccessKey(), accessKey)
                && Objects.equals(((AmazonCredentials) obj).getSecretKey(), secretKey);
    }
}
