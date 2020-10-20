package com.notronix.mws.impl.method.subscriptions;

import com.amazonservices.mws.subscriptions.model.ListRegisteredDestinationsInput;
import com.notronix.mws.api.service.AmazonAPIException;
import com.notronix.mws.api.model.AmazonMarketplace;

public class ListRegisteredDestinationsMethod extends SubscriptionsAPIMethod<ListRegisteredDestinationsInput>
{
    private String sellerId;
    private AmazonMarketplace marketplace;

    public ListRegisteredDestinationsMethod(String sellerId, AmazonMarketplace marketplace) {
        this.sellerId = sellerId;
        this.marketplace = marketplace;
    }

    @Override
    public ListRegisteredDestinationsInput buildRequest() throws AmazonAPIException {
        return new ListRegisteredDestinationsInput(sellerId, marketplace.marketplaceID());
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public AmazonMarketplace getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(AmazonMarketplace marketplace) {
        this.marketplace = marketplace;
    }
}
