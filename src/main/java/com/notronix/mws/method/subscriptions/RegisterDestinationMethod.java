package com.notronix.mws.method.subscriptions;

import com.amazonservices.mws.subscriptions.model.AttributeKeyValue;
import com.amazonservices.mws.subscriptions.model.AttributeKeyValueList;
import com.amazonservices.mws.subscriptions.model.Destination;
import com.amazonservices.mws.subscriptions.model.RegisterDestinationInput;
import com.notronix.mws.AmazonAPIException;

import java.util.Collections;

public class RegisterDestinationMethod extends SubscriptionsAPIMethod<RegisterDestinationInput>
{
    private String queueURL;
    private Destination destination;

    @Override
    public RegisterDestinationInput buildRequest() throws AmazonAPIException
    {
        AttributeKeyValue property = new AttributeKeyValue("sqsQueueUrl", queueURL);
        AttributeKeyValueList properties = new AttributeKeyValueList(Collections.singletonList(property));
        destination = new Destination("SQS", properties);

        return new RegisterDestinationInput().withDestination(destination);
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