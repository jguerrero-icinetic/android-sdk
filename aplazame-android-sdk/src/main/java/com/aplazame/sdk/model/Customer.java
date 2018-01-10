package com.aplazame.sdk.model;

import java.util.Date;
import java.util.Locale;

public class Customer {

    /**
     Customer id.
     */
    private final String id;
    /**
     The customer email.
     */
    private final String email;

    /*
     *  Optionals
     */

    /**
     (Optional) Customer type, the choices are g:guest, n:new, e:existing.
     */
    private final String type;
    /**
     (Optional) Customer gender, the choices are 0:unknown, 1:male, 2:female, 3:not applicable.
     */
    private final Integer gender;
    /**
     (Optional) Customer first name.
     */
    private final String firstName;
    /**
     (Optional) Customer last name.
     */
    private final String lastName;
    /**
     (Optional) A datetime designating when the customer account was created. (ISO 8601).
     */
    private final Date dateJoined;
    /**
     (Optional) A datetime of the customer last login. (ISO 8601).
     */
    private final Date lastLogin;
    /**
     (Optional) Customer birthday (ISO 8601).
     */
    private final Date birthday;
    /**
     (Optional) Locale for customer language preferences (ISO 639-1).
     */
    private final Locale language;
    /**
     (Optional) Customer address
     */
    private final Address address;

    public Customer(String id, String email, String type, Integer gender, String firstName, String lastName,
            Date dateJoined, Date lastLogin, Date birthday, Locale language, Address address) {
        this.id = id;
        this.email = email;
        this.type = type;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateJoined = dateJoined;
        this.lastLogin = lastLogin;
        this.birthday = birthday;
        this.language = language;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }

    public Integer getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Locale getLanguage() {
        return language;
    }

    public Address getAddress() {
        return address;
    }
}
