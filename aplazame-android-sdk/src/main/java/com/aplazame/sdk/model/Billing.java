package com.aplazame.sdk.model;

public class Billing {

    private final String firstName;
    private final String lastName;

    private final Address address;

    public Billing(String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }
}
