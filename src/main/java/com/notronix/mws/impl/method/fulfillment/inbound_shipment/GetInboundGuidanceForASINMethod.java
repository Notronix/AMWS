package com.notronix.mws.impl.method.fulfillment.inbound_shipment;

import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.ASINList;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.GetInboundGuidanceForASINRequest;
import com.notronix.mws.api.service.AmazonAPIException;
import com.notronix.mws.api.service.GeneralAmazonAPIException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.notronix.albacore.ContainerUtils.numberOf;
import static com.notronix.albacore.ContainerUtils.thereAreNo;

public class GetInboundGuidanceForASINMethod extends FulfillmentInboundShipmentAPIMethod<GetInboundGuidanceForASINRequest>
{
    private static final int MAX_ALLOWED_ASINS = 50;

    private List<String> ASINs;


    @Override
    public GetInboundGuidanceForASINRequest buildRequest() throws AmazonAPIException {
        if (thereAreNo(ASINs)) {
            throw new GeneralAmazonAPIException("No ASIN values provided");
        }

        List<String> validASINs = ASINs.parallelStream()
                .filter(StringUtils::isNotBlank).distinct().collect(Collectors.toList());

        if (thereAreNo(validASINs)) {
            throw new GeneralAmazonAPIException("No ASIN values provided");
        }

        int validCount = numberOf(validASINs);
        if (validCount > MAX_ALLOWED_ASINS) {
            throw new GeneralAmazonAPIException("Too many ASIN values provided (" + validCount + "). Maximum allowed: " + MAX_ALLOWED_ASINS);
        }

        ASINList asinList = new ASINList();

        for (String asin : validASINs) {
            asinList.withId(asin);
        }

        return new GetInboundGuidanceForASINRequest().withASINList(asinList);
    }

    public List<String> getASINs() {
        return ASINs;
    }

    public void setASINs(List<String> ASINs) {
        this.ASINs = ASINs;
    }

    public GetInboundGuidanceForASINMethod withASINs(List<String> ASINs) {
        this.ASINs = ASINs;
        return this;
    }
}
