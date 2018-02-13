package com.aplazame.sdk.model.enums;

public enum CustomerGender {

    UNKNOWN(0),

    MALE(1),

    FEMALE(2),

    NOT_APPLICABLE(3);

    private final int value;

    CustomerGender(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
