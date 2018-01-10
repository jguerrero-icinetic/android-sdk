package com.aplazame.sdk.network.model;

import com.google.gson.annotations.SerializedName;

public class BillingDto {

    @SerializedName("first_name")
    private final String firstName;
    @SerializedName("last_name")
    private final String lastName;
    @SerializedName("phone")
    private final String phone;
    @SerializedName("alt_phone")
    private final String altPhone;
    @SerializedName("street")
    private final String street;
    @SerializedName("address_addition")
    private final String addressAddition;
    @SerializedName("city")
    private final String city;
    @SerializedName("state")
    private final String state;
    @SerializedName("country")
    private final String country;
    @SerializedName("postcode")
    private final String postCode;

    public BillingDto(String firstName, String lastName, String phone, String altPhone, String street,
            String addressAddition, String city, String state, String country, String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.altPhone = altPhone;
        this.street = street;
        this.addressAddition = addressAddition;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public String getStreet() {
        return street;
    }

    public String getAddressAddition() {
        return addressAddition;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getPostCode() {
        return postCode;
    }
}
