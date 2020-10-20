package com.amazon.mws.reports.samples;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.GetReportListRequest;
import com.amazonaws.mws.model.GetReportListResponse;
import com.notronix.mws.AmazonMarketplace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

public class GetReportListAsyncSample
{
    public static void main(String... args)
    {
        final String accessKeyId = "<Your Access Key ID>";
        final String secretAccessKey = "<Your Secret Access Key>";
        final String appName = "<Your Application or Company Name>";
        final String appVersion = "<Your Application Version or Build Number or Release Date>";
        final String merchantId = "<Your Merchant ID>";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig().withServiceURL(AmazonMarketplace.US.serviceUrl());
        config.setMaxAsyncThreads(35);
        MarketplaceWebService service = new MarketplaceWebServiceClient(accessKeyId, secretAccessKey, appName, appVersion, config);

        GetReportListRequest requestOne = new GetReportListRequest().withMerchant(merchantId);
        GetReportListRequest requestTwo = new GetReportListRequest().withMerchant(merchantId);

        invokeGetReportList(service, Arrays.asList(requestOne, requestTwo));
    }

    public static void invokeGetReportList(MarketplaceWebService service, List<GetReportListRequest> requests)
    {
        List<Future<GetReportListResponse>> responses = new ArrayList<>();

        for (GetReportListRequest request : requests)
        {
            responses.add(service.getReportListAsync(request));
        }

        for (Future<GetReportListResponse> future : responses)
        {
            while (!future.isDone())
            {
                Thread.yield();
            }
            try
            {
                GetReportListResponse response = future.get();
                // Original request corresponding to this response, if needed:
                GetReportListRequest originalRequest = requests.get(responses.indexOf(future));
                System.out.println("Response request id: " + response.getResponseMetadata().getRequestId());
                System.out.println(response.getResponseHeaderMetadata());
                System.out.println();
            }
            catch (Exception e)
            {
                if (e.getCause() instanceof MarketplaceWebServiceException)
                {
                    MarketplaceWebServiceException exception = MarketplaceWebServiceException.class.cast(e.getCause());
                    System.out.println("Caught Exception: " + exception.getMessage());
                    System.out.println("Response Status Code: " + exception.getStatusCode());
                    System.out.println("Error Code: " + exception.getErrorCode());
                    System.out.println("Error Type: " + exception.getErrorType());
                    System.out.println("Request ID: " + exception.getRequestId());
                    System.out.print("XML: " + exception.getXML());
                    System.out.println("ResponseHeaderMetadata: " + exception.getResponseHeaderMetadata());
                }
                else
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
