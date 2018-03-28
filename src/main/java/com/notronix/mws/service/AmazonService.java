package com.notronix.mws.service;

import com.amazonaws.mws.model.*;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.*;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyRequest;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyResponse;
import com.amazonservices.mws.products.model.*;
import com.amazonservices.mws.subscriptions.model.*;
import com.notronix.mws.*;
import com.notronix.mws.method.AmazonAPIMethod;

public interface AmazonService
{
    AmazonMarketplace getMarketplace();

    AmazonCredentials getCredentials();

    /**
     * Returns a list of reports that were created in the previous 90 days.
     * <p>
     * A maximum of 100 results can be returned in one request. If there are additional results to return,
     * {@link GetReportListResult#hasNext HasNext} is returned set to <code>true</code> in the response. To retrieve
     * all the results, you can pass the value of the {@link GetReportListResult#nextToken NextToken} parameter to
     * {@link #getReportListByNextToken} iteratively until <code>HasNext</code> is returned set to <code>false</code>.
     *
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the AmazonCredentials does not have access to the
     *                                   AmazonMarketplace.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     * @see #getReportListByNextToken
     */
    <T extends GetReportListRequest>
    GetReportListResponse getReportList(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * Returns a list of reports using the {@link GetReportListByNextTokenResult#nextToken NextToken}, which was
     * supplied by a previous call to either {@link #getReportListByNextToken} or
     * {@link #getReportList}, where the value of <code>HasNext</code> was <code>true</code>.
     *
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     * @see #getReportList
     */
    <T extends GetReportListByNextTokenRequest>
    GetReportListByNextTokenResponse getReportListByNextToken(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * Returns the contents of a report and the <code>Content-MD5 header</code> for the returned report body. Reports
     * are retained for 90 days from the time they are generated.  You should compute the MD5 hash of the HTTP body and
     * compare that with the returned Content-MD5 header value. If they do not match, it means the body was corrupted
     * during transmission. If the report is corrupted, you should discard the result and automatically retry the
     * request up to three more times. Please notify Amazon MWS if you receive a corrupted report body.
     *
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetReportRequest>
    GetReportResponse getReport(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

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
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetReportRequestListRequest>
    GetReportRequestListResponse getReportRequestList(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    <T extends GetReportRequestListByNextTokenRequest>
    GetReportRequestListByNextTokenResponse getReportRequestListByNextToken(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends RequestReportRequest>
    RequestReportResponse requestReport(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetInboundGuidanceForASINRequest>
    GetInboundGuidanceForASINResponse getFBAInboundGuidance(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends CreateInboundShipmentRequest>
    CreateInboundShipmentResponse createInboundShipment(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends CreateInboundShipmentPlanRequest>
    CreateInboundShipmentPlanResponse createInboundShipmentPlan(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /*      Feeds Methods       */
    <T extends GetFeedSubmissionResultRequest>
    GetFeedSubmissionResultResponse getFeedSubmissionResult(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetFeedSubmissionListRequest>
    GetFeedSubmissionListResponse getFeedSubmissionList(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends SubmitFeedRequest>
    SubmitFeedResponse submitFeed(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetMyPriceForASINRequest>
    GetMyPriceForASINResponse getMyPriceForASIN(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetMatchingProductForIdRequest>
    GetMatchingProductForIdResponse getMatchingProductForId(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetCompetitivePricingForASINRequest>
    GetCompetitivePricingForASINResponse getCompetitivePricingForASIN(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetLowestOfferListingsForASINRequest>
    GetLowestOfferListingsForASINResponse getLowestOfferListingsForASIN(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetLowestPricedOffersForASINRequest>
    GetLowestPricedOffersForASINResponse getLowestPricedOffersForASIN(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends ListMatchingProductsRequest>
    ListMatchingProductsResponse listMatchingProducts(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends GetMyFeesEstimateRequest>
    GetMyFeesEstimateResponse getMyFeesEstimate(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends RegisterDestinationInput>
    RegisterDestinationResponse registerDestination(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends CreateSubscriptionInput>
    CreateSubscriptionResponse createSubscription(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends ListSubscriptionsInput>
    ListSubscriptionsResponse listSubscriptions(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends ListRegisteredDestinationsInput>
    ListRegisteredDestinationsResponse listRegisteredDestinations(AmazonAPIMethod<T> method)
            throws AmazonAPIException;

    /**
     * @param method A method that is responsible for building a correct request.
     * @param <T>    The type of request required to execute this API call.
     * @return Amazon's response to the API call.
     * @throws DeniedAccessException     If the caller with the {@link #getCredentials() AmazonCredentials} does not
     *                                   have access to the {@link #getMarketplace() AmazonMarketplace}.
     * @throws ThrottlingException       If the API call is throttled by Amazon because the throttle limits are exhausted.
     * @throws GeneralAmazonAPIException If the call fails for any other reason.
     * @throws AmazonAPIException        this exception is abstract and never directly thrown. It is the base type for
     *                                   all other thrown exceptions.
     */
    <T extends ListInventorySupplyRequest>
    ListInventorySupplyResponse listInventorySupply(AmazonAPIMethod<T> method)
            throws AmazonAPIException;
}
