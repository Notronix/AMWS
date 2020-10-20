package com.notronix.mws.api.model;

public enum IntendedBoxContentsSource
{
    NONE("NONE"),
    FEED("FEED"),
    BARCODE("2D_BARCODE");

    private String value;

    IntendedBoxContentsSource(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
