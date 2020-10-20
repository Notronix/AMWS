package com.notronix.mws.impl.method.fulfillment.inbound_shipment;

import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.Address;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.CreateInboundShipmentPlanRequest;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.InboundShipmentPlanRequestItem;
import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.InboundShipmentPlanRequestItemList;
import com.notronix.mws.api.service.AmazonAPIException;
import com.notronix.mws.api.service.GeneralAmazonAPIException;
import com.notronix.mws.api.model.LabelPrepPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.notronix.albacore.ContainerUtils.numberOf;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class CreateInboundShipmentPlanMethod extends FulfillmentInboundShipmentAPIMethod<CreateInboundShipmentPlanRequest>
{
    private Address shipFromAddress;
    private String shipToCountryCode;
    private String shipToCountrySubdivisionCode;
    private LabelPrepPreference labelPrepPreference;
    private Map<String, Integer> items = new HashMap<>();

    @Override
    public CreateInboundShipmentPlanRequest buildRequest() throws AmazonAPIException {
        if (shipFromAddress == null) {
            throw new GeneralAmazonAPIException("shipFromAddress is required.");
        }

        if (isNotBlank(shipToCountryCode) && isNotBlank(shipToCountrySubdivisionCode)) {
            throw new GeneralAmazonAPIException("Only 1 of shipToCountryCode and shipToCountrySubdivisionCode can be specified.");
        }

        List<InboundShipmentPlanRequestItem> requestItems = new ArrayList<>(numberOf(items));
        for (Map.Entry<String, Integer> item : items.entrySet()) {
            if (isNotBlank(item.getKey()) && item.getValue() != null && item.getValue() > 0) {
                requestItems.add(
                        new InboundShipmentPlanRequestItem(item.getKey(), null, null, item.getValue(), null)
                );
            }
        }

        return new CreateInboundShipmentPlanRequest()
                .withShipFromAddress(shipFromAddress)
                .withShipToCountryCode(shipToCountryCode)
                .withShipToCountrySubdivisionCode(shipToCountrySubdivisionCode)
                .withLabelPrepPreference(labelPrepPreference == null ? null : labelPrepPreference.name())
                .withInboundShipmentPlanRequestItems(new InboundShipmentPlanRequestItemList(requestItems));
    }

    public Address getShipFromAddress() {
        return shipFromAddress;
    }

    public void setShipFromAddress(Address shipFromAddress) {
        this.shipFromAddress = shipFromAddress;
    }

    public CreateInboundShipmentPlanMethod withShipFromAddress(Address shipFromAddress) {
        this.shipFromAddress = shipFromAddress;
        return this;
    }

    public String getShipToCountryCode() {
        return shipToCountryCode;
    }

    public void setShipToCountryCode(String shipToCountryCode) {
        this.shipToCountryCode = shipToCountryCode;
    }

    public CreateInboundShipmentPlanMethod withShipToCountryCode(String shipToCountryCode) {
        this.shipToCountryCode = shipToCountryCode;
        return this;
    }

    public String getShipToCountrySubdivisionCode() {
        return shipToCountrySubdivisionCode;
    }

    public void setShipToCountrySubdivisionCode(String shipToCountrySubdivisionCode) {
        this.shipToCountrySubdivisionCode = shipToCountrySubdivisionCode;
    }

    public CreateInboundShipmentPlanMethod withShipToCountrySubdivisionCode(String shipToCountrySubdivisionCode) {
        this.shipToCountrySubdivisionCode = shipToCountrySubdivisionCode;
        return this;
    }

    public LabelPrepPreference getLabelPrepPreference() {
        return labelPrepPreference;
    }

    public void setLabelPrepPreference(LabelPrepPreference labelPrepPreference) {
        this.labelPrepPreference = labelPrepPreference;
    }

    public CreateInboundShipmentPlanMethod withLabelPrepPreference(LabelPrepPreference labelPrepPreference) {
        this.labelPrepPreference = labelPrepPreference;
        return this;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public CreateInboundShipmentPlanMethod addItem(String sellerSKU, Integer quantity) {
        if (items == null) {
            items = new HashMap<>();
        }

        items.put(sellerSKU, quantity);
        return this;
    }
}
