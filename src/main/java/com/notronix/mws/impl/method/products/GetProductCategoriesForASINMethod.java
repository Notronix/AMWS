package com.notronix.mws.impl.method.products;

import com.amazonservices.mws.products.model.GetProductCategoriesForASINRequest;
import com.notronix.mws.api.AmazonAPIException;

public class GetProductCategoriesForASINMethod extends ProductsAPIMethod<GetProductCategoriesForASINRequest>
{
    private String asin;

    public GetProductCategoriesForASINMethod(String asin) {
        this.asin = asin;
    }

    @Override
    public GetProductCategoriesForASINRequest buildRequest() throws AmazonAPIException {
        return new GetProductCategoriesForASINRequest().withASIN(asin);
    }

    public String getASIN() {
        return asin;
    }

    public void setASIN(String asin) {
        this.asin = asin;
    }

    public GetProductCategoriesForASINMethod withASIN(String asin) {
        this.asin = asin;
        return this;
    }
}
