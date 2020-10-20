package com.notronix.mws.api.model;

import java.util.EnumSet;

import static org.apache.commons.lang3.StringUtils.isBlank;

@SuppressWarnings("all")
public enum CarrierCode
{
    AFL_FEDEX("AFL/Fedex"),
    AMAZON_SHIPPING("Amazon Shipping"),
    ARAMEX("Aramex"),
    BLUE_DART("BlueDart"),
    BLUE_PACKAGE("Blue Package"),
    CANADA_POST("Canada Post"),
    CHRONOPOST("Chronopost"),
    CITY_LINK("City Link"),
    DELHIVERY("Delhivery"),
    DEUTSCHE_POST("Deutsche Post"),
    DHL("DHL"),
    DHL_GLOBAL_MAIL("DHL Global Mail"),
    DPD("DPD"),
    DTDC("DTDC"),
    FASTWAY("Fastway"),
    FEDEX("FedEx"),
    FEDEX_JP("FEDEX_JP"),
    FEDEX_SMARTPOST("FedEx SmartPost"),
    FIRST_FLIGHT("First Flight"),
    GLS("GLS"),
    GO_("GO!"),
    HERMES_LOGISTIK_GRUPPE("Hermes Logistik Gruppe"),
    INDIA_POST("India Post"),
    JP_EXPRESS("JP_EXPRESS"),
    LA_POSTE("La Poste"),
    LASERSHIP("Lasership"),
    NEWGISTICS("Newgistics"),
    NIPPON_EXPRESS("NipponExpress"),
    NITTSU("NITTSU"),
    ON_TRAC("OnTrac"),
    OSM("OSM"),
    OTHER("Other"),
    OVERNITE_EXPRESS("Overnite Express"),
    PARCELFORCE("Parcelforce"),
    PARCELNET("Parcelnet"),
    POSTE_ITALIANE("Poste Italiane"),
    PROFESSIONAL("Professional"),
    ROYAL_MAIL("Royal Mail"),
    SAGAWA("SAGAWA"),
    SAGAWA_EXPRESS("SagawaExpress"),
    SDA("SDA"),
    SMARTMAIL("Smartmail"),
    STREAMLITE("Streamlite"),
    TARGET("Target"),
    TNT("TNT"),
    UPS("UPS"),
    UPS_MAIL_INNOVATIONS("UPS Mail Innovations"),
    UPSMI("UPSMI"),
    USPS("USPS"),
    YAMATO("YAMATO"),
    YAMATO_TRANSPORT("YamatoTransport"),
    YODEL("Yodel");

    private String value;

    CarrierCode(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static CarrierCode forValue(String value)
    {
        if (isBlank(value))
        {
            return null;
        }

        for (CarrierCode carrierCode : EnumSet.allOf(CarrierCode.class))
        {
            if (value.equalsIgnoreCase(carrierCode.value()))
            {
                return carrierCode;
            }
        }

        return null;
    }
}
