package com.notronix.mws.model;

import com.amazonservices.mws.products.model.Points;

public class FeeEstimateItem
{
    private String marketplaceId;
    private String asin;
    private Price listingPrice;
    private Price shipping;
    private Points points;

    public FeeEstimateItem(String marketplaceId, String asin, Price listingPrice) {
        this.marketplaceId = marketplaceId;
        this.asin = asin;
        this.listingPrice = listingPrice;
    }

    public String getMarketplaceId() {
        return marketplaceId;
    }

    public void setMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
    }

    public FeeEstimateItem withMarketplaceId(String marketplaceId) {
        this.marketplaceId = marketplaceId;
        return this;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public FeeEstimateItem withAsin(String asin) {
        this.asin = asin;
        return this;
    }

    public Price getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(Price listingPrice) {
        this.listingPrice = listingPrice;
    }

    public FeeEstimateItem withListingPrice(Price listingPrice) {
        this.listingPrice = listingPrice;
        return this;
    }

    public Price getShipping() {
        return shipping;
    }

    public void setShipping(Price shipping) {
        this.shipping = shipping;
    }

    public FeeEstimateItem withShipping(Price shipping) {
        this.shipping = shipping;
        return this;
    }

    public Points getPoints() {
        return points;
    }

    public void setPoints(Points points) {
        this.points = points;
    }

    public FeeEstimateItem withPoints() {
        this.points = points;
        return this;
    }
}
