package com.notronix.mws.method.subscriptions;

import com.amazonservices.mws.subscriptions.model.ListSubscriptionsInput;
import com.notronix.mws.AmazonAPIException;
import com.notronix.mws.AmazonMarketplace;

public class ListSubscriptionsMethod extends SubscriptionsAPIMethod<ListSubscriptionsInput>
{
    private String sellerId;
    private AmazonMarketplace marketplace;

    public ListSubscriptionsMethod(String sellerId, AmazonMarketplace marketplace) {
        this.sellerId = sellerId;
        this.marketplace = marketplace;
    }

    @Override
    public ListSubscriptionsInput buildRequest() throws AmazonAPIException {
        return new ListSubscriptionsInput()
                .withSellerId(sellerId)
                .withMarketplaceId(marketplace.marketplaceID());
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
