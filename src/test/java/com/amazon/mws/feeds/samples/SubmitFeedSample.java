package com.amazon.mws.feeds.samples;

import com.amazonaws.mws.MarketplaceWebService;
import com.amazonaws.mws.MarketplaceWebServiceException;
import com.amazonaws.mws.model.*;
//import org.apache.commons.codec.digest.DigestUtils;


//import static org.apache.commons.codec.binary.Base64.encodeBase64;

public class SubmitFeedSample
{
    public static void main(String... args) {
//        final String accessKeyId = StandardAmazonCredentials.forMarketplace(AmazonMarketplace.US).accessKey();
//        final String secretAccessKey = StandardAmazonCredentials.forMarketplace(AmazonMarketplace.US).secretKey();
//
//        final String appName = StandardAmazonCredentials.APP_NAME;
//        final String appVersion = StandardAmazonCredentials.APP_VERSION;
//
//        MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig();
//        config.setServiceURL(AmazonMarketplace.US.serviceUrl());
//
//        MarketplaceWebService service = new MarketplaceWebServiceClient(accessKeyId, secretAccessKey, appName, appVersion, config);
//        final String merchantId = StandardAmazonCredentials.forMarketplace(AmazonMarketplace.US).merchantID();
////        final IdList marketplaces = new IdList(Arrays.asList("Marketplae1", "Marketplace2"));
//
//        SubmitFeedRequest request = new SubmitFeedRequest();
//        request.setMerchant(merchantId);
//        request.setFeedType(FeedType.PRICING.value());
//
//        try
//        {
//            String content = "sku\tprice\tminimum-seller-allowed-price\tmaximum-seller-allowed-price\tquantity\tleadtime-to-ship\tbusiness-price\tquantity-price-type\tquantity-lower-bound1\tquantity-price1\tquantity-lower-bound2\tquantity-price2\tquantity-lower-bound3\tquantity-price3\tquantity-lower-bound4\tquantity-price4\tquantity-lower-bound5\tquantity-price5\tpricing_action\n";
//            content += "02608.1\t5.90\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n";
//            InputStream inputStream = new ByteArrayInputStream(content.getBytes());
////            request.setContentMD5(new String(encodeBase64(DigestUtils.md5(inputStream))));
//            request.setFeedContent(inputStream);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            System.exit(1);
//        }
//
//        invokeSubmitFeed(service, request);
    }

    private static void invokeSubmitFeed(MarketplaceWebService service, SubmitFeedRequest request) {
        try {
            SubmitFeedResponse response = service.submitFeed(request);

            System.out.println("SubmitFeed Action Response");
            System.out.println("=============================================================================");
            System.out.println();

            System.out.print("    SubmitFeedResponse");
            System.out.println();
            if (response.isSetSubmitFeedResult()) {
                System.out.print("        SubmitFeedResult");
                System.out.println();
                SubmitFeedResult submitFeedResult = response.getSubmitFeedResult();
                if (submitFeedResult.isSetFeedSubmissionInfo()) {
                    System.out.print("            FeedSubmissionInfo");
                    System.out.println();
                    FeedSubmissionInfo feedSubmissionInfo = submitFeedResult.getFeedSubmissionInfo();
                    if (feedSubmissionInfo.isSetFeedSubmissionId()) {
                        System.out.print("                FeedSubmissionId");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getFeedSubmissionId());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedType()) {
                        System.out.print("                FeedType");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getFeedType());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetSubmittedDate()) {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetFeedProcessingStatus()) {
                        System.out.print("                FeedProcessingStatus");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getFeedProcessingStatus());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetStartedProcessingDate()) {
                        System.out.print("                StartedProcessingDate");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getStartedProcessingDate());
                        System.out.println();
                    }
                    if (feedSubmissionInfo.isSetCompletedProcessingDate()) {
                        System.out.print("                CompletedProcessingDate");
                        System.out.println();
                        System.out.print("                    " + feedSubmissionInfo.getCompletedProcessingDate());
                        System.out.println();
                    }
                }
            }
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata responseMetadata = response.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            }
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();
            System.out.println();
        }
        catch (MarketplaceWebServiceException ex) {
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
