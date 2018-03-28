package com.notronix.mws.method.subscriptions;

import com.amazonservices.mws.subscriptions.model.ListSubscriptionsInput;
import com.notronix.mws.AmazonAPIException;

public class ListSubscriptionsMethod extends SubscriptionsAPIMethod<ListSubscriptionsInput>
{
    @Override
    public ListSubscriptionsInput buildRequest() throws AmazonAPIException {
        return new ListSubscriptionsInput();
    }
}
