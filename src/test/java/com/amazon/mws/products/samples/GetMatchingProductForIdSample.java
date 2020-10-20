package com.amazon.mws.products.samples;

import com.amazonservices.mws.products.MarketplaceWebServiceProductsAsyncClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsConfig;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsException;
import com.amazonservices.mws.products.model.GetMatchingProductForIdRequest;
import com.amazonservices.mws.products.model.GetMatchingProductForIdResponse;
import com.amazonservices.mws.products.model.IdListType;
import com.amazonservices.mws.products.model.ResponseHeaderMetadata;
import com.notronix.mws.api.model.AmazonMarketplace;

public class GetMatchingProductForIdSample
{
    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";
    private static final String APP_NAME = "AMWS";
    private static final String APP_VERSION = "0.0.001";

    public static void main(String[] args)
    {
        // Create a request.
        GetMatchingProductForIdRequest request = new GetMatchingProductForIdRequest();
        request.setMarketplaceId("example");
        request.setIdType("example");
        request.setIdList(new IdListType());

        try
        {
            MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig()
                    .withServiceURL(AmazonMarketplace.US.serviceUrl());
            GetMatchingProductForIdResponse response = new MarketplaceWebServiceProductsAsyncClient(ACCESS_KEY, SECRET_KEY, APP_NAME, APP_VERSION, config)
                    .getMatchingProductForId(request);

            System.out.println(response.toXML());
        }
        catch (MarketplaceWebServiceProductsException ex)
        {
            System.out.println("Service Exception:");
            ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
            if (rhmd != null)
            {
                System.out.println("RequestId: " + rhmd.getRequestId());
                System.out.println("Timestamp: " + rhmd.getTimestamp());
            }
            System.out.println("Message: " + ex.getMessage());
            System.out.println("StatusCode: " + ex.getStatusCode());
            System.out.println("ErrorCode: " + ex.getErrorCode());
            System.out.println("ErrorType: " + ex.getErrorType());

            throw ex;
        }
    }
}
