package com.aplazame.sdk.model.enums;

public enum CustomerType {

    GUEST("g"),

    NEW("n"),

    EXISTING("e");

    private final String value;

    CustomerType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
