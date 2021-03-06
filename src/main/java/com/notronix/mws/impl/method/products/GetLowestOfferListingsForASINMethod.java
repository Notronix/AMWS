package com.notronix.mws.impl.method.products;

import com.amazonservices.mws.products.model.ASINListType;
import com.amazonservices.mws.products.model.GetLowestOfferListingsForASINRequest;
import com.notronix.mws.api.AmazonAPIException;
import com.notronix.mws.api.model.ItemCondition;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static com.notronix.albacore.ContainerUtils.numberOf;

public class GetLowestOfferListingsForASINMethod extends ProductsAPIMethod<GetLowestOfferListingsForASINRequest>
{
    private Set<String> asins;
    private ItemCondition itemCondition = ItemCondition.Any;

    public GetLowestOfferListingsForASINMethod(Set<String> asins) throws IllegalArgumentException {
        Set<String> validASINs = asins.stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        if (numberOf(validASINs) > 20) {
            throw new IllegalArgumentException("A maximum of 20 ASIN values are allowed.");
        }

        this.asins = validASINs;
    }

    @Override
    public GetLowestOfferListingsForASINRequest buildRequest() throws AmazonAPIException {
        ASINListType asinList = new ASINListType();
        asinList.setASIN(new ArrayList<>(asins));

        return new GetLowestOfferListingsForASINRequest()
                .withASINList(asinList)
                .withItemCondition(itemCondition.name());
    }

    public Set<String> getAsins() {
        return asins;
    }

    public void setAsins(Set<String> asins) {
        this.asins = asins;
    }

    public ItemCondition getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(ItemCondition itemCondition) {
        this.itemCondition = itemCondition;
    }

    public GetLowestOfferListingsForASINMethod withItemCondition(ItemCondition itemCondition) {
        this.itemCondition = itemCondition;
        return this;
    }
}
