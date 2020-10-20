package com.notronix.mws.method.subscriptions;

import com.amazonservices.mws.subscriptions.model.CreateSubscriptionInput;
import com.amazonservices.mws.subscriptions.model.Destination;
import com.amazonservices.mws.subscriptions.model.Subscription;
import com.notronix.mws.AmazonAPIException;
import com.notronix.mws.AmazonMarketplace;
import com.notronix.mws.model.SubscriptionNotificationType;

public class CreateSubscriptionMethod extends SubscriptionsAPIMethod<CreateSubscriptionInput>
{
    private String sellerId;
    private AmazonMarketplace marketplace;
    private SubscriptionNotificationType notificationType;
    private Destination destination;

    public CreateSubscriptionMethod(String sellerId, AmazonMarketplace marketplace) {
        this.sellerId = sellerId;
        this.marketplace = marketplace;
    }

    @Override
    public CreateSubscriptionInput buildRequest() throws AmazonAPIException {
        return new CreateSubscriptionInput()
                .withSellerId(sellerId)
                .withMarketplaceId(marketplace.marketplaceID())
                .withSubscription(new Subscription(notificationType.name(), destination, true));
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

    public SubscriptionNotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(SubscriptionNotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public CreateSubscriptionMethod withNotificationType(SubscriptionNotificationType notificationType) {
        this.notificationType = notificationType;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public CreateSubscriptionMethod withDestination(Destination destination) {
        this.destination = destination;
        return this;
    }
}
