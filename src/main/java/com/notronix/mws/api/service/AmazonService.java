package com.notronix.mws.api.service;

import com.amazonaws.mws.model.*;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.*;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyRequest;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyResponse;
import com.amazonservices.mws.products.model.*;
import com.amazonservices.mws.sellers.model.ListMarketplaceParticipationsRequest;
import com.amazonservices.mws.sellers.model.ListMarketplaceParticipationsResponse;
import com.amazonservices.mws.subscriptions.model.*;
import com.notronix.mws.api.AmazonAPIException;
import com.notronix.mws.api.DeniedAccessException;
import com.notronix.mws.api.GeneralAmazonAPIException;
import com.notronix.mws.api.ThrottlingException;
import com.notronix.mws.api.method.AmazonAPIMethod;
import com.notronix.mws.api.model.AmazonMarketplace;

public interface AmazonService
{
    AmazonMarketplace getMarketplace();
    AmazonCredentials getCredentials();

    /**
     * Returns a list of marketplaces that the seller submitting the request can sell in, and a list of participations
     * that include seller-specific information in that marketplace.
     *
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the AmazonCredentials does not have access to the
     *                                   AmazonMarketplace.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends ListMarketplaceParticipationsRequest>
    ListMarketplaceParticipationsResponse listMarketplaceParticipations(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * Returns a list of reports that were created in the previous 90 days.
     * <p>
     * A maximum of 100 results can be returned in one request. If there are additional results to return,
     * {@link GetReportListResult#isHasNext() HasNext} is returned set to <code>true</code> in the response. To retrieve
     * all the results, you can pass the value of the {@link GetReportListResult#getNextToken() NextToken} parameter to
     * {@link #getReportListByNextToken} iteratively until <code>HasNext</code> is returned set to <code>false</code>.
     *
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the AmazonCredentials does not have access to the
     *                                   AmazonMarketplace.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     * @see #getReportListByNextToken
     */
    <R extends GetReportListRequest>
    GetReportListResponse getReportList(AmazonAPIMethod<R> method) throws AmazonAPIException;

    /**
     * Returns a list of reports using the {@link GetReportListByNextTokenResult#getNextToken() NextToken}, which was
     * supplied by a previous call to either this method or {@link #getReportList}, where the value of <code>HasNext</code>
     * was <code>true</code>.
     *
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     * @see #getReportList
     */
    <R extends GetReportListByNextTokenRequest>
    GetReportListByNextTokenResponse getReportListByNextToken(AmazonAPIMethod<R> method) throws AmazonAPIException;

    /**
     * Returns the contents of a report and the <code>Content-MD5 header</code> for the returned report body. Reports
     * are retained for 90 days from the time they are generated.  You should compute the MD5 hash of the HTTP body and
     * compare that with the returned Content-MD5 header value. If they do not match, it means the body was corrupted
     * during transmission. If the report is corrupted, you should discard the result and automatically retry the
     * request up to three more times. Please notify Amazon MWS if you receive a corrupted report body.
     *
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetReportRequest> GetReportResponse getReport(AmazonAPIMethod<R> method) throws AmazonAPIException;

    /**
     * Returns a list of report requests that match the parameters provided by the supplied <code>method</code>.
     * The list contains the <code>ReportRequestId</code> for each report request. You can obtain ReportId values by
     * passing the ReportRequestId values to {@link #getReportList}. In the first request, a maximum of 100 report
     * requests are returned. If there are additional report requests to return, <code>HasNext</code> is returned set
     * to <code>true</code> in the response. To retrieve all the results, you can pass the value of the
     * <code>NextToken</code> parameter to call {@link #getReportRequestListByNextToken} iteratively until
     * <code>HasNext</code> is returned set to <code>false</code>.
     *
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetReportRequestListRequest>
    GetReportRequestListResponse getReportRequestList(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    <R extends GetReportRequestListByNextTokenRequest>
    GetReportRequestListByNextTokenResponse getReportRequestListByNextToken(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends RequestReportRequest>
    RequestReportResponse requestReport(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetInboundGuidanceForASINRequest>
    GetInboundGuidanceForASINResponse getFBAInboundGuidance(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends CreateInboundShipmentRequest>
    CreateInboundShipmentResponse createInboundShipment(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends CreateInboundShipmentPlanRequest>
    CreateInboundShipmentPlanResponse createInboundShipmentPlan(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /*      Feeds Methods       */
    <R extends GetFeedSubmissionResultRequest>
    GetFeedSubmissionResultResponse getFeedSubmissionResult(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetFeedSubmissionListRequest>
    GetFeedSubmissionListResponse getFeedSubmissionList(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends SubmitFeedRequest>
    SubmitFeedResponse submitFeed(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetMyPriceForASINRequest>
    GetMyPriceForASINResponse getMyPriceForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetMatchingProductForIdRequest>
    GetMatchingProductForIdResponse getMatchingProductForId(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetCompetitivePricingForASINRequest>
    GetCompetitivePricingForASINResponse getCompetitivePricingForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetLowestOfferListingsForASINRequest>
    GetLowestOfferListingsForASINResponse getLowestOfferListingsForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetLowestPricedOffersForASINRequest>
    GetLowestPricedOffersForASINResponse getLowestPricedOffersForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    <R extends GetProductCategoriesForASINRequest>
    GetProductCategoriesForASINResponse getProductCategoriesForASIN(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends ListMatchingProductsRequest>
    ListMatchingProductsResponse listMatchingProducts(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends GetMyFeesEstimateRequest>
    GetMyFeesEstimateResponse getMyFeesEstimate(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends RegisterDestinationInput>
    RegisterDestinationResponse registerDestination(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends CreateSubscriptionInput>
    CreateSubscriptionResponse createSubscription(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends ListSubscriptionsInput>
    ListSubscriptionsResponse listSubscriptions(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends ListRegisteredDestinationsInput>
    ListRegisteredDestinationsResponse listRegisteredDestinations(AmazonAPIMethod<R> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <R>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <R extends ListInventorySupplyRequest>
    ListInventorySupplyResponse listInventorySupply(AmazonAPIMethod<R> method)
            throws AmazonAPIException;
}
