package com.notronix.mws.method.subscriptions;

import com.amazonservices.mws.subscriptions.model.ListRegisteredDestinationsInput;
import com.notronix.mws.AmazonAPIException;

public class ListRegisteredDestinationsMethod extends SubscriptionsAPIMethod<ListRegisteredDestinationsInput>
{
    @Override
    public ListRegisteredDestinationsInput buildRequest() throws AmazonAPIException {
        return new ListRegisteredDestinationsInput();
    }
}
