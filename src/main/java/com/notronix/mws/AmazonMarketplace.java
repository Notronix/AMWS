package com.notronix.mws;

import java.util.EnumSet;

import static org.apache.commons.lang3.StringUtils.isBlank;

public enum AmazonMarketplace
{
    CA("Canada", "A2EUQ1WTGCTBG2", "A3DWYIK6Y9EEQB", "https://mws.amazonservices.com", "CAD"),
    MX("Mexico", "A1AM78C64UM0Y8", "", "https://mws.amazonservices.com", "MXN"),
    US("United States", "ATVPDKIKX0DER", "ATVPDKIKX0DER", "https://mws.amazonservices.com", "USD"),
    DE("Germany", "A1PA6795UKMFR9", "A3JWKAKR8XB7XF", "https://mws-eu.amazonservices.com", "EUR"),
    ES("Spain", "A1RKKUPIHCS9HS", "A1AT7YVPFBWXBL", "https://mws-eu.amazonservices.com", "EUR"),
    FR("France", "A13V1IB3VIYZZH", "A1X6FK5RDHNB96", "https://mws-eu.amazonservices.com", "EUR"),
    IT("Italy", "APJ6JRA9NG5V4", "A11IL2PNWYJU7H", "https://mws-eu.amazonservices.com", "EUR"),
    UK("United Kingdom", "A1F83G8C2ARO7P", "A3P5ROKL5A1OLE", "https://mws-eu.amazonservices.com", "GBP"),
    IN("India", "A21TJRUUN4KGV", "", "https://mws.amazonservices.in/", "INR"),
    AU("Australia", "A39IBJ37TRP1C6", "A2GEX75QKRZHS8", "https://mws.amazonservices.com.au", "AUD"),
    JP("Japan", "A1VC38T7YXB528", "AN1VRQENFRJN5", "https://mws.amazonservices.jp/", "JPY"),
    CN("China", "AAHKV2X7AFYLW", "", "https://mws.amazonservices.com.cn/", "CNY"),
    BR("Brazil", "A2Q3Y263D00KWC", "", "https://mws.amazonservices.com", "BRL");

    private final String country;
    private final String marketplaceID;
    private final String sellerID;
    private final String serviceUrl;
    private final String currencyCode;

    AmazonMarketplace(String country, String marketplaceID, String sellerID, String serviceUrl, String currencyCode)
    {
        this.country = country;
        this.marketplaceID = marketplaceID;
        this.sellerID = sellerID;
        this.serviceUrl = serviceUrl;
        this.currencyCode = currencyCode;
    }

    public String country()
    {
        return country;
    }

    public String marketplaceID()
    {
        return marketplaceID;
    }

    public String getSellerID()
    {
        return sellerID;
    }

    public String serviceUrl()
    {
        return serviceUrl;
    }

    public String currencyCode()
    {
        return currencyCode;
    }

    public static AmazonMarketplace in(String country)
    {
        if (isBlank(country))
        {
            return null;
        }

        for (AmazonMarketplace marketplace : EnumSet.allOf(AmazonMarketplace.class))
        {
            if (country.equalsIgnoreCase(marketplace.name()) || country.equalsIgnoreCase(marketplace.country()))
            {
                return marketplace;
            }
        }

        return null;
    }

    public static AmazonMarketplace forId(String marketplaceID)
    {
        if (isBlank(marketplaceID))
        {
            return null;
        }

        for (AmazonMarketplace marketplace : EnumSet.allOf(AmazonMarketplace.class))
        {
            if (marketplaceID.equalsIgnoreCase(marketplace.marketplaceID()))
            {
                return marketplace;
            }
        }

        return null;
    }
}
