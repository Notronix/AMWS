package com.amazon.mws.feeds.samples;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.*;
import com.notronix.mws.AmazonMarketplace;

public class GetFeedSubmissionListSample
{
    public static void main(String... args)
    {
        final String accessKeyId = "<Your Access Key ID>";
        final String secretAccessKey = "<Your Secret Access Key>";
        final String appName = "<Your Application or Company Name>";
        final String appVersion = "<Your Application Version or Build Number or Release Date>";

        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig().withServiceURL(AmazonMarketplace.US.serviceUrl());
        MarketplaceWebService service = new MarketplaceWebServiceClient(accessKeyId, secretAccessKey, appName, appVersion, config);
        // MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();

        // MarketplaceWebService service = new MarketplaceWebServiceClient(accessKeyId, secretAccessKey, config);

        /************************************************************************
         * Uncomment to try out Mock Service that simulates Marketplace Web Service 
         * responses without calling Marketplace Web Service  service.
         *
         * Responses are loaded from local XML files. You can tweak XML files to
         * experiment with various outputs during development
         *
         * XML files available under com/amazonaws/mws/mock tree
         *
         ***********************************************************************/
        // MarketplaceWebService service = new MarketplaceWebServiceMock();

        /************************************************************************
         * Setup request parameters and uncomment invoke to try out 
         * sample for Get Feed Submission List 
         ***********************************************************************/

        /************************************************************************
         * Marketplace and Merchant IDs are required parameters for all 
         * Marketplace Web Service calls.
         ***********************************************************************/
        final String merchantId = "<Your Merchant ID>";

        GetFeedSubmissionListRequest request = new GetFeedSubmissionListRequest();
        request.setMerchant(merchantId);
        //request.setMWSAuthToken(sellerDevAuthToken);

        // @TODO: set request parameters here

        // invokeGetFeedSubmissionList(service, request);

    }


    /**
     * Get Feed Submission List  request sample
     * returns a list of feed submission identifiers and their associated metadata
     *
     * @param service instance of MarketplaceWebService service
     * @param request Action to invoke
     */
    public static void invokeGetFeedSubmissionList(MarketplaceWebService service, GetFeedSubmissionListRequest request)
    {
        try
        {

            GetFeedSubmissionListResponse response = service.getFeedSubmissionList(request);


            System.out.println("GetFeedSubmissionList Action Response");
            System.out.println("=============================================================================");
            System.out.println();

            System.out.print("    GetFeedSubmissionListResponse");
            System.out.println();
            if (response.isSetGetFeedSubmissionListResult())
            {
                System.out.print("        GetFeedSubmissionListResult");
                System.out.println();
                GetFeedSubmissionListResult getFeedSubmissionListResult = response.getGetFeedSubmissionListResult();
                if (getFeedSubmissionListResult.isSetNextToken())
                {
                    System.out.print("            NextToken");
                    System.out.println();
                    System.out.print("                " + getFeedSubmissionListResult.getNextToken());
                    System.out.println();
                }
                if (getFeedSubmissionListResult.isSetHasNext())
                {
                    System.out.print("            HasNext");
                    System.out.println();
                    System.out.print("                " + getFeedSubmissionListResult.isHasNext());
                    System.out.println();
                }
                java.util.List<FeedSubmissionInfo> feedSubmissionInfoList = getFeedSubmissionListResult.getFeedSubmissionInfoList();
                for (FeedSubmissionInfo feedSubmissionInfo : feedSubmissionInfoList)
                {
                    System.out.print("            FeedSubmissionInfo");
                    System.out.println();
                    if (feedSubmissionInfo.isSetFeedSubmissionId())
                    {
                        System.out.print("                FeedSubmissionId");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getFeedSubmissionId());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedType())
                    {
                        System.out.print("                FeedType");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getFeedType());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetSubmittedDate())
                    {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedProcessingStatus())
                    {
                        System.out.print("                FeedProcessingStatus");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getFeedProcessingStatus());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetStartedProcessingDate())
                    {
                        System.out.print("                StartedProcessingDate");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getStartedProcessingDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetCompletedProcessingDate())
                    {
                        System.out.print("                CompletedProcessingDate");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getCompletedProcessingDate());
                        System.out.println();
                    }
                }
            }
            if (response.isSetResponseMetadata())
            {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata responseMetadata = response.getResponseMetadata();
                if (responseMetadata.isSetRequestId())
                {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            }
            System.out.println();
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();


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
