package com.aplazame.sdk.model;

public class Shipping {

    /**
     Shipping first name.
     */
    private final String firstName;
    /**
     Shipping last name.
     */
    private final String lastName;
    /**
     Shipping price (tax is not included).
     */
    private final Double price;
    /**
     Shipping name.
     */
    private final String name;
    /**
     Shipping Address
     */
    private final Address address;

    /*
     *  Optionals
     */

    /**
     (Optional) Shipping tax rate.
     */
    private final Double taxRate;
    /**
     (Optional) The discount amount of the shipping.
     */
    private final Double discount;
    /**
     (Optional) The rate discount of the shipping.
     */
    private final Double discounRate;

    public Shipping(String firstName, String lastName, Double price, Double taxRate, Double discount,
            Double discounRate, String name, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
        this.taxRate = taxRate;
        this.discount = discount;
        this.discounRate = discounRate;
        this.name = name;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Double getPrice() {
        return price;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public Double getDiscount() {
        return discount;
    }

    public Double getDiscounRate() {
        return discounRate;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}
