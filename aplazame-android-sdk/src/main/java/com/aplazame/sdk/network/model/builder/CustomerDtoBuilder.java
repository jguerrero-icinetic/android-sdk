package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.AddressDto;
import com.aplazame.sdk.network.model.CustomerDto;

public class CustomerDtoBuilder {

    private String id;
    private String email;
    private String type;
    private Integer gender;
    private String firstName;
    private String lastName;
    private String dateJoined;
    private String lastLogin;
    private String birthday;
    private String language;
    private AddressDto address;

    public CustomerDtoBuilder() {}

    public CustomerDto create() {
        return new CustomerDto(id, email, type, gender, firstName, lastName, dateJoined, lastLogin, birthday, language, address);
    }

    public CustomerDtoBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public CustomerDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDtoBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public CustomerDtoBuilder withGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public CustomerDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public CustomerDtoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerDtoBuilder withDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
        return this;
    }

    public CustomerDtoBuilder withLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public CustomerDtoBuilder withBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public CustomerDtoBuilder withLanguage(String language) {
        this.language = language;
        return this;
    }

    public CustomerDtoBuilder withAddress(AddressDto address) {
        this.address = address;
        return this;
    }
}
