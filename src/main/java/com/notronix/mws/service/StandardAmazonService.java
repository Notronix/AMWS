package com.notronix.mws.service;

import com.amazonaws.mws.MarketplaceWebServiceClient;
import com.amazonaws.mws.MarketplaceWebServiceConfig;
import com.amazonaws.mws.model.*;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.FBAInboundServiceMWSAsyncClient;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.FBAInboundServiceMWSConfig;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.*;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.FBAInventoryServiceMWSAsyncClient;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.FBAInventoryServiceMWSConfig;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyRequest;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyResponse;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsAsyncClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsConfig;
import com.amazonservices.mws.products.model.*;
import com.amazonservices.mws.subscriptions.MWSSubscriptionsServiceAsyncClient;
import com.amazonservices.mws.subscriptions.MWSSubscriptionsServiceConfig;
import com.amazonservices.mws.subscriptions.model.*;
import com.notronix.mws.*;
import com.notronix.mws.method.AmazonAPIMethod;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;

public class StandardAmazonService implements AmazonService
{
    private AmazonMarketplace marketplace;
    private AmazonCredentials credentials;

    private MarketplaceWebServiceClient feedsClient;
    private final Object feedsLock = new Object();

    private FBAInboundServiceMWSAsyncClient fbaInboundClient;
    private final Object fbaInboundLock = new Object();

    private MarketplaceWebServiceProductsAsyncClient productsClient;
    private final Object productsLock = new Object();

    private MWSSubscriptionsServiceAsyncClient subscriptionsClient;
    private final Object subscriptionsLock = new Object();

    private FBAInventoryServiceMWSAsyncClient fbaInventoryClient;
    private final Object fbaInventoryLock = new Object();

    StandardAmazonService(AmazonMarketplace marketplace, AmazonCredentials credentials) {
        this.marketplace = marketplace;
        this.credentials = credentials;
    }

    @Override
    public AmazonMarketplace getMarketplace() {
        return marketplace;
    }

    @Override
    public AmazonCredentials getCredentials() {
        return credentials;
    }

    @Override
    public <T extends GetReportListRequest>
    GetReportListResponse getReportList(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getReportList(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting report list.", ex);
        }
    }

    @Override
    public <T extends GetReportListByNextTokenRequest>
    GetReportListByNextTokenResponse getReportListByNextToken(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        return null;
    }

    @Override
    public <T extends GetReportRequest>
    GetReportResponse getReport(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getReport(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting report.", ex);
        }
    }

    @Override
    public <T extends GetReportRequestListRequest>
    GetReportRequestListResponse getReportRequestList(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getReportRequestList(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting report request list.", ex);
        }
    }

    @Override
    public <T extends GetReportRequestListByNextTokenRequest>
    GetReportRequestListByNextTokenResponse getReportRequestListByNextToken(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        return null;
    }

    @Override
    public <T extends RequestReportRequest>
    RequestReportResponse requestReport(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().requestReport(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error requesting report.", ex);
        }
    }

    @Override
    public <T extends GetInboundGuidanceForASINRequest>
    GetInboundGuidanceForASINResponse getFBAInboundGuidance(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFBAInboundClient().getInboundGuidanceForASIN(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting FBA inbound guidance for ASIN.", ex);
        }
    }

    @Override
    public <T extends CreateInboundShipmentRequest>
    CreateInboundShipmentResponse createInboundShipment(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFBAInboundClient().createInboundShipment(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error creating FBA inbound shipment.", ex);
        }
    }

    @Override
    public <T extends CreateInboundShipmentPlanRequest>
    CreateInboundShipmentPlanResponse createInboundShipmentPlan(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFBAInboundClient().createInboundShipmentPlan(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error creating FBA inbound shipment plan.", ex);
        }
    }

    @Override
    public <T extends GetFeedSubmissionResultRequest>
    GetFeedSubmissionResultResponse getFeedSubmissionResult(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getFeedSubmissionResult(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting feed submission result.", ex);
        }
    }

    @Override
    public <T extends GetFeedSubmissionListRequest>
    GetFeedSubmissionListResponse getFeedSubmissionList(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getFeedSubmissionList(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting feed submission list.", ex);
        }
    }

    @Override
    public <T extends SubmitFeedRequest>
    SubmitFeedResponse submitFeed(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().submitFeed(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error submitting feed.", ex);
        }
    }

    @Override
    public <T extends GetMyPriceForASINRequest>
    GetMyPriceForASINResponse getMyPriceForASIN(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getMyPriceForASIN(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting my price for ASIN.", ex);
        }
    }

    @Override
    public <T extends GetMatchingProductForIdRequest>
    GetMatchingProductForIdResponse getMatchingProductForId(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getMatchingProductForId(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting matching products for Id.", ex);
        }
    }

    @Override
    public <T extends GetCompetitivePricingForASINRequest>
    GetCompetitivePricingForASINResponse getCompetitivePricingForASIN(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getCompetitivePricingForASIN(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting competitive pricing for ASIN.", ex);
        }
    }

    @Override
    public <T extends GetLowestOfferListingsForASINRequest>
    GetLowestOfferListingsForASINResponse getLowestOfferListingsForASIN(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getLowestOfferListingsForASIN(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting lowest offer listings for ASIN.", ex);
        }
    }

    @Override
    public <T extends GetLowestPricedOffersForASINRequest>
    GetLowestPricedOffersForASINResponse getLowestPricedOffersForASIN(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getLowestPricedOffersForASIN(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting lowest priced offers for ASIN.", ex);
        }
    }

    @Override
    public <T extends ListMatchingProductsRequest>
    ListMatchingProductsResponse listMatchingProducts(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().listMatchingProducts(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting list of matching products.", ex);
        }
    }

    @Override
    public <T extends GetMyFeesEstimateRequest>
    GetMyFeesEstimateResponse getMyFeesEstimate(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getMyFeesEstimate(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error getting my fee estimates.", ex);
        }
    }

    @Override
    public <T extends RegisterDestinationInput>
    RegisterDestinationResponse registerDestination(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getSubscriptionsClient().registerDestination(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error registering destination.", ex);
        }
    }

    @Override
    public <T extends CreateSubscriptionInput>
    CreateSubscriptionResponse createSubscription(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getSubscriptionsClient().createSubscription(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error creating subscription.", ex);
        }
    }

    @Override
    public <T extends ListSubscriptionsInput>
    ListSubscriptionsResponse listSubscriptions(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getSubscriptionsClient().listSubscriptions(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error listing subscriptions.", ex);
        }
    }

    @Override
    public <T extends ListRegisteredDestinationsInput>
    ListRegisteredDestinationsResponse listRegisteredDestinations(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getSubscriptionsClient().listRegisteredDestinations(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error listing registered destinations.", ex);
        }
    }

    @Override
    public <T extends ListInventorySupplyRequest>
    ListInventorySupplyResponse listInventorySupply(AmazonAPIMethod<T> method)
            throws AmazonAPIException {
        try {
            return getFBAInventoryClient().listInventorySupply(bootstrapRequest(method.buildRequest()));
        }
        catch (Exception ex) {
            throw handleException("Error listing inventory supply.", ex);
        }
    }

    private FBAInventoryServiceMWSAsyncClient getFBAInventoryClient() {
        if (fbaInventoryClient != null) {
            return fbaInventoryClient;
        }

        synchronized (fbaInventoryLock) {
            if (fbaInventoryClient == null) {
                FBAInventoryServiceMWSConfig config = new FBAInventoryServiceMWSConfig()
                        .withServiceURL(marketplace.serviceUrl());
                fbaInventoryClient = new FBAInventoryServiceMWSAsyncClient(credentials.getAccessKey(),
                        credentials.getSecretKey(),
                        credentials.getApplicationName(),
                        credentials.getApplicationVersion(),
                        config);
            }
        }

        return fbaInventoryClient;
    }

    private MWSSubscriptionsServiceAsyncClient getSubscriptionsClient() {
        if (subscriptionsClient != null) {
            return subscriptionsClient;
        }

        synchronized (subscriptionsLock) {
            if (subscriptionsClient == null) {
                MWSSubscriptionsServiceConfig config = new MWSSubscriptionsServiceConfig()
                        .withServiceURL(marketplace.serviceUrl());
                subscriptionsClient = new MWSSubscriptionsServiceAsyncClient(credentials.getAccessKey(),
                        credentials.getSecretKey(),
                        credentials.getApplicationName(),
                        credentials.getApplicationVersion(),
                        config);
            }
        }

        return subscriptionsClient;
    }

    private MarketplaceWebServiceProductsAsyncClient getProductsClient() {
        if (productsClient != null) {
            return productsClient;
        }

        synchronized (productsLock) {
            if (productsClient == null) {
                MarketplaceWebServiceProductsConfig config = new MarketplaceWebServiceProductsConfig()
                        .withServiceURL(marketplace.serviceUrl());
                productsClient = new MarketplaceWebServiceProductsAsyncClient(credentials.getAccessKey(),
                        credentials.getSecretKey(),
                        credentials.getApplicationName(),
                        credentials.getApplicationVersion(),
                        config);
            }
        }

        return productsClient;
    }

    private FBAInboundServiceMWSAsyncClient getFBAInboundClient() {
        if (fbaInboundClient != null) {
            return fbaInboundClient;
        }

        synchronized (fbaInboundLock) {
            if (fbaInboundClient == null) {
                FBAInboundServiceMWSConfig config = new FBAInboundServiceMWSConfig()
                        .withServiceURL(marketplace.serviceUrl());
                fbaInboundClient = new FBAInboundServiceMWSAsyncClient(credentials.getAccessKey(),
                        credentials.getSecretKey(),
                        credentials.getApplicationName(),
                        credentials.getApplicationVersion(),
                        config);
            }
        }

        return fbaInboundClient;
    }

    private MarketplaceWebServiceClient getFeedsAndReportsClient() {
        if (feedsClient != null) {
            return feedsClient;
        }

        synchronized (feedsLock) {
            if (feedsClient == null) {
                MarketplaceWebServiceConfig config = new MarketplaceWebServiceConfig()
                        .withServiceURL(marketplace.serviceUrl());
                feedsClient = new MarketplaceWebServiceClient(credentials.getAccessKey(),
                        credentials.getSecretKey(),
                        credentials.getApplicationName(),
                        credentials.getApplicationVersion(),
                        config);
            }
        }

        return feedsClient;
    }

    private <T> T bootstrapRequest(T request) {
        if (request == null) {
            return null;
        }

        setFieldIfExists(request, "marketplaceId", marketplace.marketplaceID());
        setFieldIfExists(request, "sellerId", credentials.getMerchantId());
        setFieldIfExists(request, "merchant", credentials.getMerchantId());

        return request;
    }

    private void setFieldIfExists(Object object, String fieldName, String value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            XmlElement annotation = field.getDeclaredAnnotation(XmlElement.class);

            if (annotation != null) {
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(object, value);
            }
        }
        catch (Exception ex) {
            // field not required or doesn't exist
        }
    }

    private AmazonAPIException handleException(String message, Exception exception) {
        String msg = exception.getMessage();
        if (msg != null) {
            msg = msg.toLowerCase();

            if (msg.contains("does not have access to the given marketplace")) {
                return new DeniedAccessException(message, exception);
            }

            if (msg.contains("request is throttled") || msg.contains("throttling rate exceeded")) {
                return new ThrottlingException(message, exception);
            }
        }

        return new GeneralAmazonAPIException(message, exception);
    }
}
