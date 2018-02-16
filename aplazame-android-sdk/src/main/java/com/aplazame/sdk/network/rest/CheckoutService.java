package com.aplazame.sdk.network.rest;

import com.aplazame.sdk.network.model.CheckoutAvailabilityDto;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

public interface CheckoutService {
    @GET("checkout/button")
    Call<CheckoutAvailabilityDto> checkout(@HeaderMap Map<String, String> headers, @Query("amount") Integer amount, @Query("currency") String currency);
}
