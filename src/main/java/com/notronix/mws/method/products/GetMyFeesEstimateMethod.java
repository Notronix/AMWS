package com.notronix.mws.method.products;

import com.amazonservices.mws.products.model.FeesEstimateRequest;
import com.amazonservices.mws.products.model.FeesEstimateRequestList;
import com.amazonservices.mws.products.model.GetMyFeesEstimateRequest;
import com.amazonservices.mws.products.model.PriceToEstimateFees;
import com.notronix.mws.AmazonAPIException;
import com.notronix.mws.GeneralAmazonAPIException;
import com.notronix.mws.model.FeeEstimateItem;
import com.notronix.mws.model.IdType;

import java.util.ArrayList;
import java.util.List;

import static com.notronix.albacore.ContainerUtils.numberOf;
import static com.notronix.albacore.ContainerUtils.thereAreNo;

public class GetMyFeesEstimateMethod extends ProductsAPIMethod<GetMyFeesEstimateRequest>
{
    private boolean amazonFulfilled = false;
    private List<FeeEstimateItem> items;

    @Override
    public GetMyFeesEstimateRequest buildRequest() throws AmazonAPIException {
        if (thereAreNo(items)) {
            throw new GeneralAmazonAPIException("There must be at least 1 fee request item (and not more than 20) per call.");
        }

        List<FeesEstimateRequest> feeRequests = new ArrayList<>(numberOf(items));
        for (FeeEstimateItem item : items) {
            FeesEstimateRequest feeRequest = new FeesEstimateRequest()
                    .withIdentifier(numberOf(feeRequests) + "." + item.getAsin())
                    .withMarketplaceId(item.getMarketplaceId())
                    .withIdType(IdType.ASIN.name())
                    .withIdValue(item.getAsin())
                    .withIsAmazonFulfilled(amazonFulfilled)
                    .withPriceToEstimateFees(new PriceToEstimateFees()
                            .withListingPrice(item.getListingPrice() == null ? null : item.getListingPrice().toMoneyType())
                            .withShipping(item.getShipping() == null ? null : item.getShipping().toMoneyType())
                            .withPoints(item.getPoints()));

            feeRequests.add(feeRequest);
        }

        FeesEstimateRequestList feesEstimateRequestList = new FeesEstimateRequestList();
        feesEstimateRequestList.setFeesEstimateRequest(feeRequests);

        return new GetMyFeesEstimateRequest().withFeesEstimateRequestList(feesEstimateRequestList);
    }

    public GetMyFeesEstimateMethod withAmazonFulfilled(boolean amazonFulfilled) {
        this.amazonFulfilled = amazonFulfilled;
        return this;
    }

    public GetMyFeesEstimateMethod withFeeEstimateItems(List<FeeEstimateItem> feeEstimateItems) throws IllegalArgumentException {
        if (numberOf(feeEstimateItems) > 20) {
            throw new IllegalArgumentException("A maximum of 20 items are allowed per request.");
        }

        items = new ArrayList<>(feeEstimateItems);
        return this;
    }

    public GetMyFeesEstimateMethod addFeeEstimateItem(FeeEstimateItem item) throws IllegalArgumentException {
        if (numberOf(items) >= 20) {
            throw new IllegalArgumentException("A maximum of 20 fee requests can be made per call.");
        }

        if (items == null) {
            items = new ArrayList<>(20);
        }

        items.add(item);

        return this;
    }

    public boolean isAmazonFulfilled() {
        return amazonFulfilled;
    }

    public void setAmazonFulfilled(boolean amazonFulfilled) {
        this.amazonFulfilled = amazonFulfilled;
    }

    public List<FeeEstimateItem> getItems() {
        return items;
    }

    public void setItems(List<FeeEstimateItem> items) {
        this.items = items;
    }
}
