package com.notronix.mws.method.products;

import com.amazonservices.mws.products.model.ListMatchingProductsRequest;
import com.notronix.mws.AmazonAPIException;

public class ListMatchingProductsMethod extends ProductsAPIMethod<ListMatchingProductsRequest>
{
    private String query;

    public ListMatchingProductsMethod(String query) {
        this.query = query;
    }

    @Override
    public ListMatchingProductsRequest buildRequest() throws AmazonAPIException {
        return new ListMatchingProductsRequest().withQuery(query);
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public ListMatchingProductsMethod withQuery(String query) {
        this.query = query;
        return this;
    }
}
