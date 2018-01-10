package com.aplazame.sdk.network.utils;

import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class MapperUtils {

    private static final String ISO8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String ISO8601_FORMAT_BIRTHDAY = "yyyy-MM-dd";

    public String localeCountryToString(Locale localeCountry) {
        return localeCountry.getCountry();
    }

    public String currencyToString(Currency currency) {
        return currency.getCurrencyCode();
    }

    public String localLanguageToString(Locale localeLanguage) {
        return localeLanguage.getLanguage();
    }

    public String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_FORMAT);

        return sdf.format(date);
    }

    public String dateBirthdayToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(ISO8601_FORMAT_BIRTHDAY);

        return sdf.format(date);
    }
}