package com.notronix.mws.method.products;

import com.amazonservices.mws.products.model.GetLowestPricedOffersForASINRequest;
import com.notronix.mws.AmazonAPIException;
import com.notronix.mws.GeneralAmazonAPIException;
import com.notronix.mws.model.ItemCondition;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class GetLowestPricedOffersForASINMethod extends ProductsAPIMethod<GetLowestPricedOffersForASINRequest>
{
    private String ASIN;
    private ItemCondition itemCondition;

    @Override
    public GetLowestPricedOffersForASINRequest buildRequest() throws AmazonAPIException {
        if (isBlank(ASIN)) {
            throw new GeneralAmazonAPIException("ASIN is required.");
        }

        if (itemCondition == null) {
            throw new GeneralAmazonAPIException("item condition is required.");
        }

        return new GetLowestPricedOffersForASINRequest()
                .withASIN(ASIN)
                .withItemCondition(itemCondition.name());
    }

    public String getASIN() {
        return ASIN;
    }

    public void setASIN(String ASIN) {
        this.ASIN = ASIN;
    }

    public GetLowestPricedOffersForASINMethod withASIN(String ASIN) {
        this.ASIN = ASIN;
        return this;
    }

    public ItemCondition getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(ItemCondition itemCondition) {
        this.itemCondition = itemCondition;
    }

    public GetLowestPricedOffersForASINMethod withItemCondition(ItemCondition itemCondition) {
        this.itemCondition = itemCondition;
        return this;
    }
}
