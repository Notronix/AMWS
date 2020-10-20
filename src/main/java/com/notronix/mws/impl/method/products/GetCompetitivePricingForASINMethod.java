package com.notronix.mws.impl.method.products;

import com.amazonservices.mws.products.model.ASINListType;
import com.amazonservices.mws.products.model.GetCompetitivePricingForASINRequest;
import com.notronix.mws.api.AmazonAPIException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static com.notronix.albacore.ContainerUtils.numberOf;

public class GetCompetitivePricingForASINMethod extends ProductsAPIMethod<GetCompetitivePricingForASINRequest>
{
    private Set<String> asins;

    public GetCompetitivePricingForASINMethod(Set<String> asins) throws IllegalArgumentException {
        Set<String> validASINs = asins.stream().filter(StringUtils::isNotBlank).collect(Collectors.toSet());
        if (numberOf(validASINs) > 20) {
            throw new IllegalArgumentException("A maximum of 20 ASIN values are allowed.");
        }

        this.asins = validASINs;
    }

    @Override
    public GetCompetitivePricingForASINRequest buildRequest() throws AmazonAPIException {
        ASINListType asinList = new ASINListType();
        asinList.setASIN(new ArrayList<>(asins));

        return new GetCompetitivePricingForASINRequest().withASINList(asinList);
    }

    public Set<String> getAsins() {
        return asins;
    }

    public void setAsins(Set<String> asins) {
        this.asins = asins;
    }
}
