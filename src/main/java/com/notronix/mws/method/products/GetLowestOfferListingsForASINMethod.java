package com.notronix.mws.method.products;

import com.amazonservices.mws.products.model.ASINListType;
import com.amazonservices.mws.products.model.GetLowestOfferListingsForASINRequest;
import com.notronix.mws.AmazonAPIException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static com.notronix.albacore.ContainerUtils.numberOf;

public class GetLowestOfferListingsForASINMethod extends ProductsAPIMethod<GetLowestOfferListingsForASINRequest>
{
    private Set<String> asins;

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

        return new GetLowestOfferListingsForASINRequest().withASINList(asinList);
    }

    public Set<String> getAsins() {
        return asins;
    }

    public void setAsins(Set<String> asins) {
        this.asins = asins;
    }
}
