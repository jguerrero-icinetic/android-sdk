package com.aplazame.sdk.model.builder;

import com.aplazame.sdk.model.Address;
import com.aplazame.sdk.model.Billing;

public class BillingBuilder {

    private String firstName;
    private String lastName;

    private Address address;

    public BillingBuilder() {}

    public Billing create() {
        return new Billing(firstName, lastName, address);
    }

    public BillingBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BillingBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BillingBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }
}
