package com.notronix.mws.method.fulfillment.inbound_shipment;

import com.amazonservices.mws.FulfillmentInboundShipment._2010_10_01.model.*;
import com.notronix.mws.AmazonAPIException;
import com.notronix.mws.GeneralAmazonAPIException;
import com.notronix.mws.model.IntendedBoxContentsSource;
import com.notronix.mws.model.LabelPrepPreference;
import com.notronix.mws.model.ShipmentStatus;

import java.util.ArrayList;
import java.util.List;

import static com.notronix.albacore.ContainerUtils.thereAreNo;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class CreateInboundShipmentMethod extends FulfillmentInboundShipmentAPIMethod<CreateInboundShipmentRequest>
{
    private String shipmentId;

    private String shipmentName;
    private Address shipFromAddress;
    private String destinationFulfillmentCenterId;
    private LabelPrepPreference labelPrepPreference;
    private ShipmentStatus shipmentStatus;
    private Boolean areCasesRequired;
    private IntendedBoxContentsSource intendedBoxContentsSource;

    private List<InboundShipmentItem> shipmentItems = new ArrayList<>();

    @Override
    public CreateInboundShipmentRequest buildRequest() throws AmazonAPIException {
        if (isBlank(shipmentId)) {
            throw new GeneralAmazonAPIException("shipmentId is required.");
        }

        if (isBlank(shipmentName)) {
            throw new GeneralAmazonAPIException("shipmentName is required.");
        }

        if (shipFromAddress == null) {
            throw new GeneralAmazonAPIException("shipFromAddress is required.");
        }

        if (isBlank(destinationFulfillmentCenterId)) {
            throw new GeneralAmazonAPIException("destinationFulfillmentCenterId is required.");
        }

        if (labelPrepPreference == null) {
            throw new GeneralAmazonAPIException("labelPrepPreference is required.");
        }

        if (shipmentStatus == null || (!ShipmentStatus.WORKING.equals(shipmentStatus) && !ShipmentStatus.SHIPPED.equals(shipmentStatus))) {
            throw new GeneralAmazonAPIException("shipmentStatus is required.  Must be either WORKING or SHIPPED.");
        }

        if (thereAreNo(shipmentItems)) {
            throw new GeneralAmazonAPIException("At least 1 shipment item is required.");
        }

        for (InboundShipmentItem shipmentItem : shipmentItems) {
            if (shipmentItem == null
                    || isBlank(shipmentItem.getSellerSKU())
                    || shipmentItem.getQuantityShipped() < 1) {
                throw new GeneralAmazonAPIException("Every shipment item must have a sellerSKU and a positive value quantity shipped.");
            }
        }

        InboundShipmentHeader inboundShipmentHeader =
                new InboundShipmentHeader(
                        shipmentName,
                        shipFromAddress,
                        destinationFulfillmentCenterId,
                        areCasesRequired,
                        shipmentStatus.name(),
                        labelPrepPreference.name()
                ).withIntendedBoxContentsSource(intendedBoxContentsSource == null ? null : intendedBoxContentsSource.getValue());

        return new CreateInboundShipmentRequest()
                .withShipmentId(shipmentId)
                .withInboundShipmentHeader(inboundShipmentHeader)
                .withInboundShipmentItems(new InboundShipmentItemList(shipmentItems));
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public CreateInboundShipmentMethod withShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
        return this;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public void setShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
    }

    public CreateInboundShipmentMethod withShipmentName(String shipmentName) {
        this.shipmentName = shipmentName;
        return this;
    }

    public Address getShipFromAddress() {
        return shipFromAddress;
    }

    public void setShipFromAddress(Address shipFromAddress) {
        this.shipFromAddress = shipFromAddress;
    }

    public CreateInboundShipmentMethod withShipFromAddress(Address shipFromAddress) {
        this.shipFromAddress = shipFromAddress;
        return this;
    }

    public String getDestinationFulfillmentCenterId() {
        return destinationFulfillmentCenterId;
    }

    public void setDestinationFulfillmentCenterId(String destinationFulfillmentCenterId) {
        this.destinationFulfillmentCenterId = destinationFulfillmentCenterId;
    }

    public CreateInboundShipmentMethod withDestinationFulfillmentCenterId(String destinationFulfillmentCenterId) {
        this.destinationFulfillmentCenterId = destinationFulfillmentCenterId;
        return this;
    }

    public LabelPrepPreference getLabelPrepPreference() {
        return labelPrepPreference;
    }

    public void setLabelPrepPreference(LabelPrepPreference labelPrepPreference) {
        this.labelPrepPreference = labelPrepPreference;
    }

    public CreateInboundShipmentMethod withLabelPrepPreference(LabelPrepPreference labelPrepPreference) {
        this.labelPrepPreference = labelPrepPreference;
        return this;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public CreateInboundShipmentMethod withShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
        return this;
    }

    public Boolean getAreCasesRequired() {
        return areCasesRequired;
    }

    public void setAreCasesRequired(Boolean areCasesRequired) {
        this.areCasesRequired = areCasesRequired;
    }

    public CreateInboundShipmentMethod withAreCasesRequired(Boolean areCasesRequired) {
        this.areCasesRequired = areCasesRequired;
        return this;
    }

    public IntendedBoxContentsSource getIntendedBoxContentsSource() {
        return intendedBoxContentsSource;
    }

    public void setIntendedBoxContentsSource(IntendedBoxContentsSource intendedBoxContentsSource) {
        this.intendedBoxContentsSource = intendedBoxContentsSource;
    }

    public CreateInboundShipmentMethod withIntendedBoxContentsSource(IntendedBoxContentsSource intendedBoxContentsSource) {
        this.intendedBoxContentsSource = intendedBoxContentsSource;
        return this;
    }

    public List<InboundShipmentItem> getShipmentItems() {
        return shipmentItems;
    }

    public void setShipmentItems(List<InboundShipmentItem> shipmentItems) {
        this.shipmentItems = shipmentItems;
    }

    public CreateInboundShipmentMethod addShipmentItem(String sellerSKU,
                                                       Integer quantity,
                                                       String releaseDate,
                                                       List<PrepDetails> prepDetails) throws AmazonAPIException {
        if (isBlank(sellerSKU)) {
            throw new GeneralAmazonAPIException("sellerSKU is required.");
        }

        if (quantity == null || quantity < 1) {
            throw new GeneralAmazonAPIException("A positive value quantity is required.");
        }

        if (shipmentItems == null) {
            shipmentItems = new ArrayList<>();
        }

        shipmentItems.add(
                new InboundShipmentItem()
                        .withSellerSKU(sellerSKU)
                        .withQuantityShipped(quantity)
                        .withReleaseDate(releaseDate)
                        .withPrepDetailsList(prepDetails == null ? null : new PrepDetailsList(prepDetails))
        );

        return this;
    }
}
