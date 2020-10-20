package com.amazon.mws.feeds.samples;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.SubmitFeedRequest;
import com.amazonaws.mws.model.SubmitFeedResponse;
import com.notronix.mws.model.FeedType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class SubmitFeedAsyncSample
{
    public static void main(String... args)
    {
        final String accessKeyId = "<Your Access Key ID>";
        final String secretAccessKey = "<Your Secret Access Key>";

        final String appName = "<Your Application or Company Name>";
        final String appVersion = "<Your Application Version or Build Number or Release Date>";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
        config.setServiceURL("https://mws.amazonservices.com/");
        config.setMaxAsyncThreads(35);

        MarketplaceWebService service = new MarketplaceWebServiceClient(
                accessKeyId, secretAccessKey, appName, appVersion, config);

        final String merchantId = "<Your Merchant ID>";

        SubmitFeedRequest requestOne = new SubmitFeedRequest();
        requestOne.setMerchant(merchantId);
        requestOne.setFeedType(FeedType.PRICING.value());

        SubmitFeedRequest requestTwo = new SubmitFeedRequest();

        try {
            requestOne.setFeedContent(new FileInputStream("my-feed-1.xml"));
            requestTwo.setMerchant(merchantId);
            requestTwo.setFeedType(FeedType.PRICING.value());
            requestTwo.setFeedContent(new FileInputStream("my-feed-2.xml"));
        } catch (FileNotFoundException ex) {
            // continue
        }

        List<SubmitFeedRequest> requests = new ArrayList<>();
        requests.add(requestOne);
        requests.add(requestTwo);

        invokeSubmitFeed(service, requests);
    }

    private static void invokeSubmitFeed(MarketplaceWebService service, List<SubmitFeedRequest> requests)
    {
        List<Future<SubmitFeedResponse>> responses = new ArrayList<>();
        for (SubmitFeedRequest request : requests) {
            responses.add(service.submitFeedAsync(request));
        }
        for (Future<SubmitFeedResponse> future : responses) {
            while (!future.isDone()) {
                Thread.yield();
            }
            try {
                SubmitFeedResponse response = future.get();
                // Original request corresponding to this response, if needed:
                SubmitFeedRequest originalRequest = requests.get(responses.indexOf(future));
                System.out.println("Original Request: " + originalRequest.toString());
                System.out.println("Response request id: " + response.getResponseMetadata().getRequestId());
                System.out.println(response.getResponseHeaderMetadata());
                System.out.println();
            } catch (Exception e) {
                if (e.getCause() instanceof MarketplaceWebServiceException) {
                    MarketplaceWebServiceException exception = MarketplaceWebServiceException.class.cast(e.getCause());
                    System.out.println("Caught Exception: " + exception.getMessage());
                    System.out.println("Response Status Code: " + exception.getStatusCode());
                    System.out.println("Error Code: " + exception.getErrorCode());
                    System.out.println("Error Type: " + exception.getErrorType());
                    System.out.println("Request ID: " + exception.getRequestId());
                    System.out.print("XML: " + exception.getXML());
                    System.out.println("ResponseHeaderMetadata: " + exception.getResponseHeaderMetadata());
                } else {
                    e.printStackTrace();
                }
            }
        }
    }
}
