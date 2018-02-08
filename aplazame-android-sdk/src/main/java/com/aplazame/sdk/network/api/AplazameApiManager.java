package com.aplazame.sdk.network.api;

import com.aplazame.sdk.model.Checkout;
import com.aplazame.sdk.network.authenticator.AuthInterceptor;
import com.aplazame.sdk.network.mapper.CheckoutToCheckoutDtoMapper;
import com.aplazame.sdk.network.model.CheckoutAvailabilityDto;
import com.aplazame.sdk.network.model.CheckoutDto;
import com.aplazame.sdk.network.response.AvailabilityCallback;
import com.aplazame.sdk.network.rest.CheckoutService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AplazameApiManager {

    private String token;
    private Boolean debug;
    private static final String BASE_URL = "https://api.aplazame.com/";
    private static final String INITIALIZE_CHECKOUT_URL =
            "https://checkout.aplazame.com/?public-key=%1$s%2$s&post-message=true&os=android&webview=true";
    private static final String ANDROID_JS_INTERFACE_NAME = "AplazameAndroidSDK";

    private static final String POST_MESSAGE_CHECKOUT_DATA =
            "window.postMessage({aplazame: 'checkout', event: 'checkout-data', data: %s}, '*');";

    private static final String SANDBOX_ENABLED_PARAM = "&sandbox=true";

    private static final String EVENT_LISTENER =
            "window.addEventListener('message', function (e) {\n" +
                    "  var message = e.data;\n" +
                    "\n" +
                    "  // ignoring messages that does not comes from checkout\n" +
                    "  if( message.aplazame !== 'checkout' ) return;\n" +
                    "\n" +
                    "  switch( message.event ) {\n" +
                    "    case 'checkout-ready':\n" +
                        ANDROID_JS_INTERFACE_NAME + ".sendReadyEvent();\n" +
                    "      break;\n" +
                    "    case 'get-checkout-data':\n" +
                        ANDROID_JS_INTERFACE_NAME + ".sendCheckoutData();\n" +
                    "      break;\n" +
                    "    case 'status-change':\n" +
                        ANDROID_JS_INTERFACE_NAME + ".sendStatusChangeEvent(message.status);\n" +
                    "      break;\n" +
                    "    case 'close':\n" +
                        ANDROID_JS_INTERFACE_NAME + ".sendCloseEvent(message.result);\n" +
                    "      break;\n" +
                    "  }\n" +
                    "\n" +
                    "});";

    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_APLAZAME_DEBUG = "application/vnd.aplazame.sandbox.v1+json";
    private static final String HEADER_APLAZAME = "application/vnd.aplazame.v1+json";

    public AplazameApiManager(String token, Boolean debug) {
        this.token = token;
        this.debug = debug;
    }

    public void checkAvailability(Checkout checkout, final AvailabilityCallback callback) {
        Map<String, String> header = headerRequest();
        Retrofit retrofit = configureRetrofit();
        CheckoutToCheckoutDtoMapper mapper = new CheckoutToCheckoutDtoMapper();

        CheckoutDto checkoutDto = mapper.transformDomainToDto(checkout);

        final CheckoutService service = retrofit.create(CheckoutService.class);

        Call<CheckoutAvailabilityDto> call = service.checkout(header,
                checkoutDto.getOrder().getTotalAmount(), checkoutDto.getOrder().getCurrency());
        call.enqueue(new Callback<CheckoutAvailabilityDto>() {
            @Override
            public void onResponse(Call<CheckoutAvailabilityDto> call, Response<CheckoutAvailabilityDto> response) {
                if (response.isSuccessful() && response.body().isAllowed()) {
                    callback.onAvailabilitySuccess();
                } else {
                    callback.onAvailabilityFailure();
                }
            }

            @Override
            public void onFailure(Call<CheckoutAvailabilityDto> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public void checkAvailability(Double amount, String currency, final AvailabilityCallback callback) {
        Map<String, String> header = headerRequest();
        Retrofit retrofit = configureRetrofit();

        final CheckoutService service = retrofit.create(CheckoutService.class);

        Call<CheckoutAvailabilityDto> call = service.checkout(header, amount, currency);
        call.enqueue(new Callback<CheckoutAvailabilityDto>() {
            @Override
            public void onResponse(Call<CheckoutAvailabilityDto> call, Response<CheckoutAvailabilityDto> response) {
                if (response.isSuccessful() && response.body().isAllowed()) {
                    callback.onAvailable();
                } else {
                    callback.onNotAvailable();
                }
            }

            @Override
            public void onFailure(Call<CheckoutAvailabilityDto> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    public String requestCheckout(Checkout checkout) {
        CheckoutToCheckoutDtoMapper mapper = new CheckoutToCheckoutDtoMapper();
        CheckoutDto checkoutDto = mapper.transformDomainToDto(checkout);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Type type = new TypeToken<CheckoutDto>(){}.getType();
        String json = gson.toJson(checkoutDto, type);

        return String.format(POST_MESSAGE_CHECKOUT_DATA, json);
    }

    public String initializeCheckoutUrl() {
        return String.format(INITIALIZE_CHECKOUT_URL, token, debug ? SANDBOX_ENABLED_PARAM : "");
    }

    public String addEventListener() {
        return EVENT_LISTENER;
    }

    public String getAndroidJsInterfaceName() {
        return ANDROID_JS_INTERFACE_NAME;
    }

    private Retrofit configureRetrofit() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.interceptors().add(interceptor);
        client.addInterceptor(new AuthInterceptor(token));

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private Map<String, String> headerRequest() {
        Map<String, String> header = new HashMap<>();

        if (debug) {
            header.put(HEADER_ACCEPT, HEADER_APLAZAME_DEBUG);
        } else {
            header.put(HEADER_ACCEPT, HEADER_APLAZAME);
        }

        return header;
    }
}
