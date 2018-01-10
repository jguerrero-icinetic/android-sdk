package com.aplazame.sdk.network.model;

import com.google.gson.annotations.SerializedName;

public class CustomerDto {

    @SerializedName("id")
    private final String id;
    @SerializedName("email")
    private final String email;

    /*
     *  Optionals
     */

    @SerializedName("type")
    private final String type;
    @SerializedName("gender")
    private final Integer gender;
    @SerializedName("first_name")
    private final String firstName;
    @SerializedName("last_name")
    private final String lastName;
    @SerializedName("date_joined")
    private final String dateJoined;
    @SerializedName("last_login")
    private final String lastLogin;
    @SerializedName("birthday")
    private final String birthday;
    @SerializedName("language")
    private final String language;
    @SerializedName("address")
    private final AddressDto address;

    public CustomerDto(String id, String email, String type, Integer gender, String firstName, String lastName,
            String dateJoined, String lastLogin, String birthday, String language, AddressDto address) {
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

    public String getDateJoined() {
        return dateJoined;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLanguage() {
        return language;
    }

    public AddressDto getAddress() {
        return address;
    }
}
