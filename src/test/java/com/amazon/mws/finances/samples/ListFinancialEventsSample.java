package com.amazon.mws.finances.samples;

import com.amazon.mws.finances._2015_05_01.MWSFinancesService;
import com.amazon.mws.finances._2015_05_01.MWSFinancesServiceClient;
import com.amazon.mws.finances._2015_05_01.MWSFinancesServiceException;
import com.amazon.mws.finances._2015_05_01.model.ListFinancialEventsRequest;
import com.amazon.mws.finances._2015_05_01.model.ListFinancialEventsResponse;
import com.amazon.mws.finances._2015_05_01.model.ResponseHeaderMetadata;
import com.amazonservices.mws.client.MwsUtl;

import javax.xml.datatype.XMLGregorianCalendar;

public class ListFinancialEventsSample
{
    public static void main(String[] args)
    {
        MWSFinancesService service = MWSFinancesServiceSampleConfig.getClient();

        // Create a request.
        ListFinancialEventsRequest request = new ListFinancialEventsRequest();
        String sellerId = "example";
        request.setSellerId(sellerId);
        String mwsAuthToken = "example";
        request.setMWSAuthToken(mwsAuthToken);
        Integer maxResultsPerPage = 1;
        request.setMaxResultsPerPage(maxResultsPerPage);
        String amazonOrderId = "example";
        request.setAmazonOrderId(amazonOrderId);
        String financialEventGroupId = "example";
        request.setFinancialEventGroupId(financialEventGroupId);
        XMLGregorianCalendar postedAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
        request.setPostedAfter(postedAfter);
        XMLGregorianCalendar postedBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
        request.setPostedBefore(postedBefore);

        // Make the call.
        ListFinancialEventsSample.invokeListFinancialEvents(service, request);

    }

    private static ListFinancialEventsResponse invokeListFinancialEvents(MWSFinancesService client, ListFinancialEventsRequest request)
    {
        try
        {
            // Call the service.
            ListFinancialEventsResponse response = client.listFinancialEvents(request);
            ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
            // We recommend logging every the request id and timestamp of every call.
            System.out.println("Response:");
            System.out.println("RequestId: " + rhmd.getRequestId());
            System.out.println("Timestamp: " + rhmd.getTimestamp());
            String responseXml = response.toXML();
            System.out.println(responseXml);
            return response;
        }
        catch (MWSFinancesServiceException ex)
        {
            // Exception properties are important for diagnostics.
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
}
