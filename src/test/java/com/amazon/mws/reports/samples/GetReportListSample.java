package com.amazon.mws.reports.samples;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.GetReportListRequest;
import com.amazonaws.mws.model.GetReportListResponse;
import com.notronix.mws.api.model.AmazonMarketplace;

public class GetReportListSample
{
    public static void main(String... args)
    {
        final String merchantId = "A4D5YUCGK9W9X";
        final String accessKeyId = "AKIAJEU6KSJ7SZCDHWFQ";
        final String secretAccessKey = "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";
        final String appName = "AMWS";
        final String appVersion = "0.0.001";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig().withServiceURL(AmazonMarketplace.US.serviceUrl());
        MarketplaceWebService service = new MarketplaceWebServiceClient(accessKeyId, secretAccessKey, appName, appVersion, config);

        GetReportListRequest request = new GetReportListRequest();
        request.setMerchant(merchantId);

        invokeGetReportList(service, request);
    }

    /**
     * Get Report List  request sample
     * returns a list of reports; by default the most recent ten reports,
     * regardless of their acknowledgement status
     *
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
    private static void invokeGetReportList(MarketplaceWebService service, GetReportListRequest request)
    {
        try
        {
            GetReportListResponse response = service.getReportList(request);
            System.out.println("3454823177017127");
        }
        catch (MarketplaceWebServiceException ex)
        {
            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
    }
}
