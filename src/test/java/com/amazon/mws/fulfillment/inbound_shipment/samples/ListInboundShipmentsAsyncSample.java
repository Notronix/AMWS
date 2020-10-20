package com.amazon.mws.fulfillment.inbound_shipment.samples;

import java.util.*;
import java.util.concurrent.*;
import javax.xml.datatype.XMLGregorianCalendar;

import com.amazonservices.mws.client.*;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.*;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.*;

public class ListInboundShipmentsAsyncSample
{
    public static List<Object> invokeListInboundShipments(FBAInboundServiceMWSAsync client, List<ListInboundShipmentsRequest> requestList)
    {
        // Call the service async.
        List<Future<ListInboundShipmentsResponse>> futureList = new ArrayList<Future<ListInboundShipmentsResponse>>();
        for (ListInboundShipmentsRequest request : requestList)
        {
            Future<ListInboundShipmentsResponse> future = client.listInboundShipmentsAsync(request);
            futureList.add(future);
        }

        List<Object> responseList = new ArrayList<Object>();
        for (Future<ListInboundShipmentsResponse> future : futureList)
        {
            Object xresponse;
            try
            {
                ListInboundShipmentsResponse response = future.get();
                ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
                // We recommend logging every the request id and timestamp of every call.
                System.out.println("Response:");
                System.out.println("RequestId: " + rhmd.getRequestId());
                System.out.println("Timestamp: " + rhmd.getTimestamp());
                String responseXml = response.toXML();
                System.out.println(responseXml);
                xresponse = response;
            }
            catch (ExecutionException ee)
            {
                Throwable cause = ee.getCause();
                if (cause instanceof FBAInboundServiceMWSException)
                {
                    // Exception properties are important for diagnostics.
                    FBAInboundServiceMWSException ex =
                            (FBAInboundServiceMWSException) cause;
                    ResponseHeaderMetadata rhmd = ex.getResponseHeaderMetadata();
                    System.out.println("Service Exception:");
                    System.out.println("RequestId: " + rhmd.getRequestId());
                    System.out.println("Timestamp: " + rhmd.getTimestamp());
                    System.out.println("Message: " + ex.getMessage());
                    System.out.println("StatusCode: " + ex.getStatusCode());
                    System.out.println("ErrorCode: " + ex.getErrorCode());
                    System.out.println("ErrorType: " + ex.getErrorType());
                    xresponse = ex;
                }
                else
                {
                    xresponse = cause;
                }
            }
            catch (Exception e)
            {
                xresponse = e;
            }
            responseList.add(xresponse);
        }

        return responseList;
    }

    public static void main(String[] args)
    {
        FBAInboundServiceMWSAsyncClient client = FBAInboundServiceMWSSampleConfig.getAsyncClient();

        // Create a request list.
        List<ListInboundShipmentsRequest> requestList = new ArrayList<ListInboundShipmentsRequest>();
        ListInboundShipmentsRequest request = new ListInboundShipmentsRequest();
        String sellerId = "example";
        request.setSellerId(sellerId);
        String mwsAuthToken = "example";
        request.setMWSAuthToken(mwsAuthToken);
        String marketplace = "example";
        request.setMarketplace(marketplace);
        ShipmentStatusList shipmentStatusList = new ShipmentStatusList();
        request.setShipmentStatusList(shipmentStatusList);
        ShipmentIdList shipmentIdList = new ShipmentIdList();
        request.setShipmentIdList(shipmentIdList);
        XMLGregorianCalendar lastUpdatedBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
        request.setLastUpdatedBefore(lastUpdatedBefore);
        XMLGregorianCalendar lastUpdatedAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
        request.setLastUpdatedAfter(lastUpdatedAfter);
        requestList.add(request);

        // Make the calls.
        ListInboundShipmentsAsyncSample.invokeListInboundShipments(client, requestList);
    }
}
