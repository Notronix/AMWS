package com.amazon.mws.products.samples;

import com.amazonservices.mws.products.*;
import com.amazonservices.mws.products.model.*;

public class GetMyFeesEstimateSample
{
    private static GetMyFeesEstimateResponse invokeGetMyFeesEstimate(MarketplaceWebServiceProducts client, GetMyFeesEstimateRequest request)
    {
        try
        {
            GetMyFeesEstimateResponse response = client.getMyFeesEstimate(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();

            System.out.println("Response:");
            System.out.println("RequestId: " + rhmd.getRequestId());
            System.out.println("Timestamp: " + rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);

            return response;
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

    public static void main(String[] args)
    {
        MarketplaceWebServiceProductsClient client = MarketplaceWebServiceProductsSampleConfig.getService();

        GetMyFeesEstimateRequest request = new GetMyFeesEstimateRequest();
        String sellerId = "example";
        request.setSellerId(sellerId);
        String mwsAuthToken = "example";
        request.setMWSAuthToken(mwsAuthToken);
        FeesEstimateRequestList feesEstimateRequestList = new FeesEstimateRequestList();
        request.setFeesEstimateRequestList(feesEstimateRequestList);

        GetMyFeesEstimateSample.invokeGetMyFeesEstimate(client, request);
    }
}
