package com.notronix.mws.api.model;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicReference;

import static com.notronix.albacore.Optionals.ofBlankable;
import static java.util.Objects.requireNonNull;

/**
 * https://docs.developer.amazonservices.com/en_US/dev_guide/DG_Endpoints.html
 */
@SuppressWarnings({"unused", "SpellCheckingInspection"})
public enum AmazonMarketplace
{
    // Table 1. North America region
    BR("Brazil", "A2Q3Y263D00KWC", "", "https://mws.amazonservices.com", "BRL"),
    CA("Canada", "A2EUQ1WTGCTBG2", "A3DWYIK6Y9EEQB", "https://mws.amazonservices.ca", "CAD"),
    MX("Mexico", "A1AM78C64UM0Y8", "", "https://mws.amazonservices.com.mx", "MXN"),
    US("United States", "ATVPDKIKX0DER", "ATVPDKIKX0DER", "https://mws.amazonservices.com", "USD"),

    // Table 2. Europe region
    AE("United Arab Emirates (U.A.E.)", "A2VIGQ35RCS4UG", "", "https://mws.amazonservices.ae", "AED"),
    DE("Germany", "A1PA6795UKMFR9", "A3JWKAKR8XB7XF", "https://mws-eu.amazonservices.com", "EUR"),
    EG("Egypt", "ARBP9OOSHTCHU", "", "https://mws-eu.amazonservices.com", "EGP"),
    ES("Spain", "A1RKKUPIHCS9HS", "A1AT7YVPFBWXBL", "https://mws-eu.amazonservices.com", "EUR"),
    FR("France", "A13V1IB3VIYZZH", "A1X6FK5RDHNB96", "https://mws-eu.amazonservices.com", "EUR"),
    UK("United Kingdom", "A1F83G8C2ARO7P", "A3P5ROKL5A1OLE", "https://mws-eu.amazonservices.com", "GBP"),
    IN("India", "A21TJRUUN4KGV", "", "https://mws.amazonservices.in", "INR"),
    IT("Italy", "APJ6JRA9NG5V4", "A11IL2PNWYJU7H", "https://mws-eu.amazonservices.com", "EUR"),
    NL("Netherlands", "A1805IZSGTT6HS", "", "https://mws-eu.amazonservices.com", "EUR"),
    SA("Saudi Arabia", "A17E79C6D8DWNP", "", "https://mws-eu.amazonservices.com", "SAR"),
    SE("Sweden", "A2NODRKZP88ZB9", "", "https://mws-eu.amazonservices.com", "SEK"),
    TR("Turkey", "A33AVAJ2PDY3EV", "", "https://mws-eu.amazonservices.com", "TRY"),

    // Table 3. Far East region
    SG("Singapore", "A19VAU5U5O7RUS", "", "https://mws-fe.amazonservices.com", "SGD"),
    AU("Australia", "A39IBJ37TRP1C6", "A2GEX75QKRZHS8", "https://mws.amazonservices.com.au", "AUD"),
    JP("Japan", "A1VC38T7YXB528", "AN1VRQENFRJN5", "\thttps://mws.amazonservices.jp", "JPY"),
    CN("China", "AAHKV2X7AFYLW", "", "https://mws.amazonservices.com.cn/", "CNY");

    private final String country;
    private final String marketplaceID;
    private final String sellerID;
    private final String serviceUrl;
    private final String currencyCode;

    AmazonMarketplace(String country, String marketplaceID, String sellerID, String serviceUrl, String currencyCode) {
        this.country = requireNonNull(country);
        this.marketplaceID = requireNonNull(marketplaceID);
        this.sellerID = sellerID;
        this.serviceUrl = requireNonNull(serviceUrl);
        this.currencyCode = requireNonNull(currencyCode);
    }

    public String country() {
        return country;
    }

    public String marketplaceID() {
        return marketplaceID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public String serviceUrl() {
        return serviceUrl;
    }

    public String currencyCode() {
        return currencyCode;
    }

    public static AmazonMarketplace in(String country) {
        AtomicReference<AmazonMarketplace> foundMarketplace = new AtomicReference<>();
        ofBlankable(country).flatMap(theCountry -> EnumSet.allOf(AmazonMarketplace.class).stream()
                .filter(marketplace -> matchesCountry(marketplace, theCountry))
                .findAny()).ifPresent(foundMarketplace::set);

        return foundMarketplace.get();
    }

    public static AmazonMarketplace forId(String marketplaceID) {
        AtomicReference<AmazonMarketplace> foundMarketplace = new AtomicReference<>();
        ofBlankable(marketplaceID).flatMap(theID -> EnumSet.allOf(AmazonMarketplace.class).stream()
                .filter(marketplace -> matchesID(marketplace, theID))
                .findAny()).ifPresent(foundMarketplace::set);

        return foundMarketplace.get();
    }

    private static boolean matchesCountry(AmazonMarketplace marketplace, String country) {
        return country.equalsIgnoreCase(marketplace.name()) || country.equalsIgnoreCase(marketplace.country());
    }

    private static boolean matchesID(AmazonMarketplace marketplace, String marketplaceID) {
        return marketplaceID.equalsIgnoreCase(marketplace.marketplaceID());
    }
}
