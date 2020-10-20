package com.amazon.mws.products.samples;

import com.amazonservices.mws.products.MarketplaceWebServiceProductsAsyncClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsConfig;

public class MarketplaceWebServiceProductsSampleConfig
{
    private static final String accessKey = "replaceWithAccessKey";
    private static final String secretKey = "replaceWithSecretKey";
    private static final String appName = "replaceWithAppName";
    private static final String appVersion = "replaceWithAppVersion";
    private static final String serviceURL = "replaceWithServiceURL";

    private static MarketplaceWebServiceProductsAsyncClient service = null;

    public static MarketplaceWebServiceProductsClient getService()
    {
        return getAsyncService();
    }

    public static synchronized MarketplaceWebServiceProductsAsyncClient getAsyncService()
    {
        if (service == null)
        {
            MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig().withServiceURL(serviceURL);
            service = new MarketplaceWebServiceProductsAsyncClient(accessKey, secretKey, appName, appVersion, config, null);
        }

        return service;
    }
}
