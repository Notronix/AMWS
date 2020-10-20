package com.notronix.mws.impl.method.subscriptions;

import com.amazonservices.mws.subscriptions.model.AttributeKeyValue;
import com.amazonservices.mws.subscriptions.model.AttributeKeyValueList;
import com.amazonservices.mws.subscriptions.model.Destination;
import com.amazonservices.mws.subscriptions.model.RegisterDestinationInput;
import com.notronix.mws.api.AmazonAPIException;
import com.notronix.mws.api.model.AmazonMarketplace;

import java.util.Collections;

public class RegisterDestinationMethod extends SubscriptionsAPIMethod<RegisterDestinationInput>
{
    private String sellerId;
    private AmazonMarketplace marketplace;
    private String queueURL;
    private Destination destination;

    public RegisterDestinationMethod(String sellerId, AmazonMarketplace marketplace) {
        this.sellerId = sellerId;
        this.marketplace = marketplace;
    }

    @Override
    public RegisterDestinationInput buildRequest() throws AmazonAPIException
    {
        AttributeKeyValue property = new AttributeKeyValue("sqsQueueUrl", queueURL);
        AttributeKeyValueList properties = new AttributeKeyValueList(Collections.singletonList(property));
        destination = new Destination("SQS", properties);

        return new RegisterDestinationInput()
                .withSellerId(sellerId)
                .withMarketplaceId(marketplace.marketplaceID())
                .withDestination(destination);
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

    public String getQueueURL()
    {
        return queueURL;
    }

    public void setQueueURL(String queueURL)
    {
        this.queueURL = queueURL;
    }

    public RegisterDestinationMethod withQueueURL(String queueURL)
    {
        this.queueURL = queueURL;
        return this;
    }

    public Destination getDestination() {
        return destination;
    }
}
