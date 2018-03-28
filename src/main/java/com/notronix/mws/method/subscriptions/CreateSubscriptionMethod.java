package com.notronix.mws.method.subscriptions;

import com.amazonservices.mws.subscriptions.model.CreateSubscriptionInput;
import com.amazonservices.mws.subscriptions.model.Destination;
import com.amazonservices.mws.subscriptions.model.Subscription;
import com.notronix.mws.AmazonAPIException;
import com.notronix.mws.model.SubscriptionNotificationType;

public class CreateSubscriptionMethod extends SubscriptionsAPIMethod<CreateSubscriptionInput>
{
    private SubscriptionNotificationType notificationType;
    private Destination destination;

    @Override
    public CreateSubscriptionInput buildRequest() throws AmazonAPIException {
        return new CreateSubscriptionInput()
                .withSubscription(new Subscription(notificationType.name(), destination, true));
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
