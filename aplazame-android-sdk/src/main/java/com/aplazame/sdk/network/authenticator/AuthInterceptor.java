package com.aplazame.sdk.network.authenticator;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static String AUTHORIZATION = "Authorization";
    private static String BEARER = "Bearer";
    private String token;

    public AuthInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader(AUTHORIZATION, BEARER + " " + token)
                .build();

        return chain.proceed(request);
    }
}
