package com.aplazame.sdk.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aplazame.sdk.AplazameSDK;
import com.aplazame.sdk.model.Address;
import com.aplazame.sdk.model.Article;
import com.aplazame.sdk.model.Billing;
import com.aplazame.sdk.model.Checkout;
import com.aplazame.sdk.model.Customer;
import com.aplazame.sdk.model.Merchant;
import com.aplazame.sdk.model.Order;
import com.aplazame.sdk.model.Shipping;
import com.aplazame.sdk.model.builder.AddressBuilder;
import com.aplazame.sdk.model.builder.ArticleBuilder;
import com.aplazame.sdk.model.builder.BillingBuilder;
import com.aplazame.sdk.model.builder.CheckoutBuilder;
import com.aplazame.sdk.model.builder.CustomerBuilder;
import com.aplazame.sdk.model.builder.MerchantBuilder;
import com.aplazame.sdk.model.builder.OrderBuilder;
import com.aplazame.sdk.model.builder.ShippingBuilder;
import com.aplazame.sdk.model.enums.CustomerGender;
import com.aplazame.sdk.model.enums.CustomerType;
import com.aplazame.sdk.network.response.AvailabilityCallback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TOKEN = "12093289b594f50d3971e4719eedd5c314ceb6ba";
    private static final boolean DEBUG = true;

    private ProgressBar progressBar;
    private EditText accessToken;
    private TextView check;
    private View layerButton;
    private RelativeLayout goCheckout;

    private boolean isAplazameAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        initializeImages();

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                if (!accessToken.getText().toString().trim().isEmpty()) {
                    AplazameSDK.setConfiguration(accessToken.getText().toString().trim(), DEBUG);

                    /*
                        You can overload this method with
                        AplazameSDK.checkAvailability(Double amount, String currency, AvailabilityCallback responseCallback)
                        After that, to use the checkout, you must use this method:
                        AplazameSDK.setCheckout(Checkout checkout);
                     */
                    AplazameSDK.checkAvailability(createCheckout(), new AvailabilityCallback() {
                        @Override
                        public void onAvailable() {
                            // Enable checkout button for instance
                            progressBar.setVisibility(View.GONE);
                            layerButton.setVisibility(View.GONE);
                            isAplazameAvailable = true;
                        }

                        @Override
                        public void onNotAvailable() {
                            // Hide the checkout button for instance
                            progressBar.setVisibility(View.GONE);
                            layerButton.setVisibility(View.VISIBLE);
                            isAplazameAvailable = false;
                            Toast.makeText(MainActivity.this, getString(R.string.aplazame_unavailable_token), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure() {
                            // Hide the checkout button for instance (unknown error)
                            progressBar.setVisibility(View.GONE);
                            layerButton.setVisibility(View.VISIBLE);
                            isAplazameAvailable = false;
                            Toast.makeText(MainActivity.this, getString(R.string.aplazame_unavailable_error), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        goCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAplazameAvailable) {
                    Intent goWebViewAplazame = new Intent(MainActivity.this, WebViewAplazameActivity.class);
                    startActivity(goWebViewAplazame);
                }
            }
        });
    }

    private void initializeViews() {
        progressBar = findViewById(R.id.progress);
        accessToken = findViewById(R.id.access_token);
        check = findViewById(R.id.check);
        layerButton = findViewById(R.id.layer_button);
        goCheckout = findViewById(R.id.go_checkout);

        accessToken.setText(TOKEN);
    }

    private void initializeImages() {
        Picasso.with(this).load("https://i.imgur.com//1nIay4X.jpg").into((ImageView) findViewById(R.id.image_article1));
        Picasso.with(this).load("https://i.imgur.com//CZ5UPbl.jpg").into((ImageView) findViewById(R.id.image_article2));
        Picasso.with(this).load("https://i.imgur.com//4j2ib6w.jpg").into((ImageView) findViewById(R.id.image_article3));
    }

    /**
     *  Fake Checkout: This is an example of checkout
     */

    private Checkout createCheckout() {
        Checkout checkout = new CheckoutBuilder()
                .withMerchant(createMerchant())
                .withOrder(createOrder())
                .withCustomer(createCustomer())
                .withBilling(createBilling())
                .withShipping(createShipping())
                .create();

        return checkout;
    }

    private Merchant createMerchant() {
        Merchant merchant = new MerchantBuilder()
                .withCloseOnSuccess(false)
                .withConfirmOnCheckout(true)
                .withTimeoutCheckout(2880l) // Default value
                .withTimeoutExtra(2880l)    // Default value
                .create();

        return merchant;
    }

    private Billing createBilling() {
        Address address = new AddressBuilder()
                .withState("Madrid")
                .withCity("Madrid")
                .withPostCode("28020")
                .withStreet("Torre Picasso, Plaza Pablo Ruiz Picasso 1")
                .withAddressAddition("Esquina")
                .withCountry(Locale.US)
                .withPhone("555555555")
                .withAltPhone("666666666")
                .create();

        Billing billing = new BillingBuilder()
                .withLastName("Costello")
                .withFirstName("Frank")
                .withAddress(address)
                .create();

        return billing;
    }

    private Customer createCustomer() {
        Address address = new AddressBuilder()
                .withState("Madrid")
                .withCity("Madrid")
                .withPostCode("28020")
                .withStreet("Torre Picasso, Plaza Pablo Ruiz Picasso 1")
                .withAddressAddition("Esquina")
                .withCountry(Locale.US)
                .withPhone("555555555")
                .withAltPhone("666666666")
                .create();

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.YEAR, 1960);
        Date dateBirthday = calendar.getTime();

        Customer customer = new CustomerBuilder()
                .withGender(CustomerGender.MALE.getValue())
                .withEmail("dev@aplazame.com")
                .withId(String.valueOf(Math.abs(new Random().nextInt() * 100000)))
                .withType(CustomerType.EXISTING.getValue())
                .withDateJoined(new Date(System.currentTimeMillis()))
                .withBirthday(dateBirthday)
                .withLastLogin(new Date(System.currentTimeMillis()))
                .withLanguage(Locale.getDefault())
                .withFirstName("Frank")
                .withLastName("Costello")
                .withAddress(address)
                .create();

        return customer;
    }

    private Shipping createShipping() {
        Address address = new AddressBuilder()
                .withState("Madrid")
                .withCity("Madrid")
                .withPostCode("28020")
                .withStreet("Torre Picasso, Plaza Pablo Ruiz Picasso 1")
                .withAddressAddition("Esquina")
                .withCountry(Locale.US)
                .withPhone("555555555")
                .withAltPhone("666666666")
                .create();

        Shipping shipping = new ShippingBuilder()
                .withFirstName("Fernando")
                .withLastName("Cabello")
                .withName("Fernando Envío")
                .withPrice(10.0)
                .withTaxRate(1.0)
                .withDiscount(1.0)
                .withDiscountRate(1.0)
                .withAddress(address)
                .create();

        return shipping;
    }

    private Order createOrder() {
        List<Article> articles = new ArrayList<>();

        Article article1 = new ArticleBuilder()
                .withId("id1")
                .withDiscountRate(0.0)
                .withName("RELOJ EN ORO BLANCO DE 18 QUILATES Y DIAMANTES")
                .withDiscount(0.0)
                .withDescription("description")
                .withTaxRate(0.0)
                .withQuantity(2)
                .withImageUrl("https://i.imgur.com//CZ5UPbl.jpg")
                .withPrice(3993.0)
                .withUrl("http://www.chanel.com/es_ES/Relojeria/relojes_joyer%C3%ADa#reloj-en-oro-blanco-de-18-quilates-y-diamantes-J10211")
                .create();

        Article article2 = new ArticleBuilder()
                .withId("id2")
                .withDiscountRate(0.0)
                .withName("N°5 EAU PREMIERE SPRAY")
                .withDiscount(0.0)
                .withDescription("description")
                .withTaxRate(0.0)
                .withQuantity(1)
                .withImageUrl("https://i.imgur.com//1nIay4X.jpg")
                .withPrice(3509.0)
                .withUrl("https://www.chanel.com/en_US/fragrance-beauty/fragrance-no5-no5-88145/sku/138083")
                .create();

        Article article3 = new ArticleBuilder()
                .withId("id3")
                .withDiscountRate(0.0)
                .withName("ILLUSION D'OMBRE")
                .withDiscount(0.0)
                .withDescription("description")
                .withTaxRate(0.0)
                .withQuantity(1)
                .withImageUrl("https://i.imgur.com//4j2ib6w.jpg")
                .withPrice(1573.0)
                .withUrl("https://www.chanel.com/en_US/fragrance-beauty/makeup-eyeshadow-illusion-d%27ombre-122567")
                .create();

        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        Order order = new OrderBuilder()
                .withTotalAmount(2000.0)
                .withCurrency(Currency.getInstance(Locale.getDefault()))
                .withDiscount(362.0)
                .withDiscountRate(1.0)
                .withCartRate(1.0)
                .withCartDiscountRate(1.0)
                .withId(String.valueOf(Math.abs(new Random().nextInt() * 100000)))
                .withTaxRate(20.0)
                .withArticles(articles)
                .create();

        return order;
    }
}
