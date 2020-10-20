package com.notronix.mws.impl.method.sellers;

import com.amazonservices.mws.sellers.model.ListMarketplaceParticipationsRequest;
import com.notronix.mws.api.AmazonAPIException;

public class ListMarketplaceParticipationsMethod extends SellersAPIMethod<ListMarketplaceParticipationsRequest>
{
    @Override
    public ListMarketplaceParticipationsRequest buildRequest() throws AmazonAPIException {
        return new ListMarketplaceParticipationsRequest();
    }
}
