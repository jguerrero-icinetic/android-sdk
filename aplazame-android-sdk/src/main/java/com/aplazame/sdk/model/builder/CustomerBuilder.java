package com.aplazame.sdk.model.builder;

import com.aplazame.sdk.model.Address;
import com.aplazame.sdk.model.Customer;

import java.util.Date;
import java.util.Locale;

public class CustomerBuilder {

    private String id;
    private String email;
    private String type;
    private Integer gender;
    private String firstName;
    private String lastName;
    private Date dateJoined;
    private Date lastLogin;
    private Date birthday;
    private Locale language;
    private Address address;

    public CustomerBuilder() {}

    public Customer create() {
        return new Customer(id, email, type, gender, firstName, lastName, dateJoined, lastLogin, birthday, language, address);
    }

    public CustomerBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CustomerBuilder withGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public CustomerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerBuilder withDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
        return this;
    }

    public CustomerBuilder withLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public CustomerBuilder withBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public CustomerBuilder withLanguage(Locale language) {
        this.language = language;
        return this;
    }

    public CustomerBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }
}
