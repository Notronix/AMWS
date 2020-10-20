package com.notronix.mws.impl.method.fulfillment.inventory;

import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyRequest;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.SellerSkuList;
import com.notronix.mws.api.model.ResponseGroup;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static com.notronix.albacore.ContainerUtils.numberOf;

public class ListInventorySupplyMethod extends FulfillmentInventoryAPIMethod<ListInventorySupplyRequest>
{
    private Set<String> sellerSKUs;
    private ResponseGroup responseGroup = ResponseGroup.Basic;

    public ListInventorySupplyMethod(Set<String> sellerSKUs) {
        Set<String> validSkus = sellerSKUs.stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        if (numberOf(validSkus) > 50) {
            throw new IllegalArgumentException("A maximum of 50 SKUs are allowed.");
        }
        this.sellerSKUs = validSkus;
    }

    @Override
    public ListInventorySupplyRequest buildRequest() {
        return new ListInventorySupplyRequest()
                .withResponseGroup(responseGroup.name())
                .withSellerSkus(new SellerSkuList(new ArrayList<>(sellerSKUs)));
    }

    public ResponseGroup getResponseGroup() {
        return responseGroup;
    }

    public void setResponseGroup(ResponseGroup responseGroup) {
        this.responseGroup = responseGroup;
    }

    public ListInventorySupplyMethod withResponseGroup(ResponseGroup responseGroup) {
        this.responseGroup = responseGroup;
        return this;
    }

    public Set<String> getSellerSKUs() {
        return sellerSKUs;
    }

    public void setSellerSKUs(Set<String> sellerSKUs) {
        this.sellerSKUs = sellerSKUs;
    }
}
