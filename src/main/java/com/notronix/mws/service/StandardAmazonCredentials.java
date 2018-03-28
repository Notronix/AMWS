package com.notronix.mws.service;

import com.notronix.mws.AmazonMarketplace;

import java.util.Objects;

public final class StandardAmazonCredentials implements AmazonCredentials
{
    static final StandardAmazonCredentials EMPTY_CREDENTIALS = new StandardAmazonCredentials(null,
            null, null, null, null, null);

    private AmazonMarketplace marketplace;
    private String merchantId;
    private String accessKey;
    private String secretKey;
    private String applicationName;
    private String applicationVersion;

    public StandardAmazonCredentials(AmazonMarketplace marketplace,
                                     String merchantId,
                                     String accessKey,
                                     String secretKey,
                                     String applicationName,
                                     String applicationVersion) {
        this.marketplace = marketplace;
        this.merchantId = merchantId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
    }

    @Override
    public boolean isEmpty() {
        return this.marketplace == null &&
                this.merchantId == null &&
                this.accessKey == null &&
                this.secretKey == null &&
                this.applicationName == null &&
                this.applicationVersion == null;
    }

    @Override
    public AmazonMarketplace getMarketplace() {
        return marketplace;
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

    @Override
    public String getApplicationName() {
        return applicationName;
    }

    @Override
    public String getApplicationVersion() {
        return applicationVersion;
    }

    @Override
    public int hashCode() {
        return 911
                + (marketplace == null ? 0 : marketplace.hashCode())
                + (merchantId == null ? 0 : merchantId.hashCode())
                + (accessKey == null ? 0 : accessKey.hashCode())
                + (secretKey == null ? 0 : secretKey.hashCode())
                + (applicationName == null ? 0 : applicationName.hashCode())
                + (applicationVersion == null ? 0 : applicationVersion.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StandardAmazonCredentials
                && Objects.equals(marketplace, ((StandardAmazonCredentials) obj).getMarketplace())
                && Objects.equals(merchantId, ((StandardAmazonCredentials) obj).getMerchantId())
                && Objects.equals(accessKey, ((StandardAmazonCredentials) obj).getAccessKey())
                && Objects.equals(secretKey, ((StandardAmazonCredentials) obj).getSecretKey())
                && Objects.equals(applicationName, ((StandardAmazonCredentials) obj).getApplicationName())
                && Objects.equals(applicationVersion, ((StandardAmazonCredentials) obj).getApplicationVersion());
    }
}
