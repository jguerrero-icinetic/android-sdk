package com.aplazame.sdk.model;

import com.aplazame.sdk.network.utils.MapperUtils;

import java.util.Date;
import java.util.Locale;

public class Customer {

    public String id;
    public String email;
    public String type;
    public Integer gender;
    public String first_name;
    public String last_name;
    public String date_joined;
    public String last_login;
    public String birthday;
    public String language;
    public Address address;

    public void setDateJoined(Date date_joined) {
        this.date_joined = MapperUtils.dateToString(date_joined);
    }

    public void setLastLogin(Date last_login) {
        this.last_login = MapperUtils.dateToString(last_login);
    }

    public void setBirthday(Date birthday) {
        this.birthday = MapperUtils.dateBirthdayToString(birthday);
    }

    public void setLanguage(Locale language) {
        this.language = MapperUtils.localLanguageToString(language);
    }
}
