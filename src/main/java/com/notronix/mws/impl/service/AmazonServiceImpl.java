package com.notronix.mws.impl.service;

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
import com.amazonservices.mws.sellers.MarketplaceWebServiceSellersAsyncClient;
import com.amazonservices.mws.sellers.MarketplaceWebServiceSellersConfig;
import com.amazonservices.mws.sellers.model.ListMarketplaceParticipationsRequest;
import com.amazonservices.mws.sellers.model.ListMarketplaceParticipationsResponse;
import com.amazonservices.mws.subscriptions.MWSSubscriptionsServiceAsyncClient;
import com.amazonservices.mws.subscriptions.MWSSubscriptionsServiceConfig;
import com.amazonservices.mws.subscriptions.model.*;
import com.notronix.mws.api.method.AmazonAPIMethod;
import com.notronix.mws.api.model.AmazonMarketplace;
import com.notronix.mws.api.service.*;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

public class AmazonServiceImpl implements AmazonService
{
    private static final String appName = "AMWS";
    private static final String appVersion = "1.0.0001";
    
    private final AmazonMarketplace marketplace;
    private final AmazonCredentials credentials;

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

    private MarketplaceWebServiceSellersAsyncClient sellersClient;
    private final Object sellersLock = new Object();

    public AmazonServiceImpl(AmazonMarketplace marketplace, AmazonCredentials credentials) {
        this.marketplace = requireNonNull(marketplace);
        this.credentials = requireNonNull(credentials);
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
    public <R extends ListMarketplaceParticipationsRequest>
    ListMarketplaceParticipationsResponse listMarketplaceParticipations(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getSellersClient().listMarketplaceParticipations(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting marketplace participations.", ex);
        }
    }

    @Override
    public <R extends GetReportListRequest>
    GetReportListResponse getReportList(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getReportList(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting report list.", ex);
        }
    }

    @Override
    public <R extends GetReportListByNextTokenRequest>
    GetReportListByNextTokenResponse getReportListByNextToken(AmazonAPIMethod<R> method) {
        return null;
    }

    @Override
    public <R extends GetReportRequest>
    GetReportResponse getReport(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getReport(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting report.", ex);
        }
    }

    @Override
    public <R extends GetReportRequestListRequest>
    GetReportRequestListResponse getReportRequestList(AmazonAPIMethod<R> method) throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getReportRequestList(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting report request list.", ex);
        }
    }

    @Override
    public <R extends GetReportRequestListByNextTokenRequest>
    GetReportRequestListByNextTokenResponse getReportRequestListByNextToken(AmazonAPIMethod<R> method) {
        return null;
    }

    @Override
    public <R extends RequestReportRequest>
    RequestReportResponse requestReport(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().requestReport(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error requesting report.", ex);
        }
    }

    @Override
    public <R extends GetInboundGuidanceForASINRequest>
    GetInboundGuidanceForASINResponse getFBAInboundGuidance(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFBAInboundClient().getInboundGuidanceForASIN(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting FBA inbound guidance for ASIN.", ex);
        }
    }

    @Override
    public <R extends CreateInboundShipmentRequest>
    CreateInboundShipmentResponse createInboundShipment(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFBAInboundClient().createInboundShipment(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error creating FBA inbound shipment.", ex);
        }
    }

    @Override
    public <R extends CreateInboundShipmentPlanRequest>
    CreateInboundShipmentPlanResponse createInboundShipmentPlan(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFBAInboundClient().createInboundShipmentPlan(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error creating FBA inbound shipment plan.", ex);
        }
    }

    @Override
    public <R extends GetFeedSubmissionResultRequest>
    GetFeedSubmissionResultResponse getFeedSubmissionResult(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getFeedSubmissionResult(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting feed submission result.", ex);
        }
    }

    @Override
    public <R extends GetFeedSubmissionListRequest>
    GetFeedSubmissionListResponse getFeedSubmissionList(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().getFeedSubmissionList(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting feed submission list.", ex);
        }
    }

    @Override
    public <R extends SubmitFeedRequest>
    SubmitFeedResponse submitFeed(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFeedsAndReportsClient().submitFeed(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error submitting feed.", ex);
        }
    }

    @Override
    public <R extends GetMyPriceForASINRequest>
    GetMyPriceForASINResponse getMyPriceForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getMyPriceForASIN(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting my price for ASIN.", ex);
        }
    }

    @Override
    public <R extends GetMatchingProductForIdRequest>
    GetMatchingProductForIdResponse getMatchingProductForId(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getMatchingProductForId(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting matching products for Id.", ex);
        }
    }

    @Override
    public <R extends GetCompetitivePricingForASINRequest>
    GetCompetitivePricingForASINResponse getCompetitivePricingForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getCompetitivePricingForASIN(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting competitive pricing for ASIN.", ex);
        }
    }

    @Override
    public <R extends GetLowestOfferListingsForASINRequest>
    GetLowestOfferListingsForASINResponse getLowestOfferListingsForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getLowestOfferListingsForASIN(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting lowest offer listings for ASIN.", ex);
        }
    }

    @Override
    public <R extends GetLowestPricedOffersForASINRequest>
    GetLowestPricedOffersForASINResponse getLowestPricedOffersForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getLowestPricedOffersForASIN(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting lowest priced offers for ASIN.", ex);
        }
    }

    @Override
    public <R extends GetProductCategoriesForASINRequest>
    GetProductCategoriesForASINResponse getProductCategoriesForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getProductCategoriesForASIN(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting product categories for ASIN.", ex);
        }
    }

    @Override
    public <R extends ListMatchingProductsRequest>
    ListMatchingProductsResponse listMatchingProducts(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().listMatchingProducts(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting list of matching products.", ex);
        }
    }

    @Override
    public <R extends GetMyFeesEstimateRequest>
    GetMyFeesEstimateResponse getMyFeesEstimate(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getProductsClient().getMyFeesEstimate(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error getting my fee estimates.", ex);
        }
    }

    @Override
    public <R extends RegisterDestinationInput>
    RegisterDestinationResponse registerDestination(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getSubscriptionsClient().registerDestination(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error registering destination.", ex);
        }
    }

    @Override
    public <R extends CreateSubscriptionInput>
    CreateSubscriptionResponse createSubscription(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getSubscriptionsClient().createSubscription(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error creating subscription.", ex);
        }
    }

    @Override
    public <R extends ListSubscriptionsInput>
    ListSubscriptionsResponse listSubscriptions(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getSubscriptionsClient().listSubscriptions(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error listing subscriptions.", ex);
        }
    }

    @Override
    public <R extends ListRegisteredDestinationsInput>
    ListRegisteredDestinationsResponse listRegisteredDestinations(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getSubscriptionsClient().listRegisteredDestinations(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
            throw handleException("Error listing registered destinations.", ex);
        }
    }

    @Override
    public <R extends ListInventorySupplyRequest>
    ListInventorySupplyResponse listInventorySupply(AmazonAPIMethod<R> method)
            throws AmazonAPIException {
        try {
            return getFBAInventoryClient().listInventorySupply(bootstrapRequest(method.buildRequest()));
        } catch (Exception ex) {
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
                        credentials.getSecretKey(), appName, appVersion, config);
            }
        }

        return fbaInventoryClient;
    }

    private MarketplaceWebServiceSellersAsyncClient getSellersClient() {
        if (sellersClient != null) {
            return sellersClient;
        }

        synchronized (sellersLock) {
            if (sellersClient == null) {
                MarketplaceWebServiceSellersConfig config = new MarketplaceWebServiceSellersConfig()
                        .withServiceURL(marketplace.serviceUrl());
                sellersClient = new MarketplaceWebServiceSellersAsyncClient(credentials.getAccessKey(),
                        credentials.getSecretKey(), appName, appVersion, config);
            }
        }

        return sellersClient;
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
                        credentials.getSecretKey(), appName, appVersion, config);
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
                        credentials.getSecretKey(), appName, appVersion, config);
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
                        credentials.getSecretKey(), appName, appVersion, config);
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
                        credentials.getSecretKey(), appName, appVersion, config);
            }
        }

        return feedsClient;
    }

    private <R> R bootstrapRequest(R request) {
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
        } catch (Exception ex) {
            // field not required or doesn't exist
        }
    }

    private AmazonAPIException handleException(String message, Exception exception) {
        return containsThrottling(exception.getMessage()) ? new ThrottlingException(message, exception)
                : (containsAccessDenied(exception.getMessage()) ? new DeniedAccessException(message, exception)
                : new GeneralAmazonAPIException(message, exception));
    }

    private boolean containsAccessDenied(String msg) {
        return containsIgnoreCase(msg, "Access denied") || containsIgnoreCase(msg, "does not have access");
    }

    private boolean containsThrottling(String msg) {
        return containsIgnoreCase(msg, "throttled") || containsIgnoreCase(msg, "throttling");
    }
}
