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

                    AplazameSDK.checkAvailability(120.50, "EUR", new AvailabilityCallback() {
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
                    AplazameSDK.setCheckout(createCheckout());
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
        Checkout checkout = new Checkout();
        checkout.merchant = createMerchant();
        checkout.order = createOrder();
        checkout.customer = createCustomer();
        checkout.billing = createBilling();
        checkout.shipping = createShipping();

        return checkout;
    }

    private Merchant createMerchant() {
        Merchant merchant = new Merchant();
        merchant.close_on_success = false;
        merchant.confirm_on_checkout = true;
        merchant.timeout_checkout = 2880; // Default value
        merchant.timeout_extra = 2880;    // Default value

        return merchant;
    }

    private Billing createBilling() {
        Billing billing = new Billing();
        billing.state = "Madrid";
        billing.city = "Madrid";
        billing.postcode = "28020";
        billing.street = "Torre Picasso, Plaza Pablo Ruiz Picasso 1";
        billing.address_addition = "Esquina";
        billing.setCountry(Locale.US);
        billing.phone = "555555555";
        billing.alt_phone = "666666666";
        billing.last_name = "Costello";
        billing.first_name = "Frank";

        return billing;
    }

    private Customer createCustomer() {
        Address address = new Address();
        address.state = "Madrid";
        address.city = "Madrid";
        address.postcode = "28020";
        address.street = "Torre Picasso, Plaza Pablo Ruiz Picasso 1";
        address.address_addition = "Esquina";
        address.setCountry(Locale.US);
        address.phone = "555555555";
        address.alt_phone = "666666666";

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.DAY_OF_MONTH, 10);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.YEAR, 1960);
        Date dateBirthday = calendar.getTime();

        Customer customer = new Customer();
        customer.gender = CustomerGender.MALE.getValue();
        customer.email = "dev@aplazame.com";
        customer.id = String.valueOf(Math.abs(new Random().nextInt() * 100000));
        customer.type = CustomerType.EXISTING.getValue();
        customer.setDateJoined(new Date(System.currentTimeMillis()));
        customer.setBirthday(dateBirthday);
        customer.setLastLogin(new Date(System.currentTimeMillis()));
        customer.setLanguage(Locale.getDefault());
        customer.first_name = "Frank";
        customer.last_name = "Costello";
        customer.address = address;

        return customer;
    }

    private Shipping createShipping() {
        Shipping shipping = new Shipping();
        shipping.state = "Madrid";
        shipping.city = "Madrid";
        shipping.postcode = "28020";
        shipping.street = "Torre Picasso, Plaza Pablo Ruiz Picasso 1";
        shipping.address_addition = "Esquina";
        shipping.setCountry(Locale.US);
        shipping.phone = "555555555";
        shipping.alt_phone = "666666666";
        shipping.first_name = "Fernando";
        shipping.last_name = "Cabello";
        shipping.name = "Fernando Envío";
        shipping.setPrice(10.0);
        shipping.setTaxRate(0.21);
        shipping.setDiscount(1.0);
        shipping.setDiscountRate(1.0);

        return shipping;
    }

    private Order createOrder() {
        List<Article> articles = new ArrayList<>();

        Article article1 = new Article();
        article1.id = "id1";
        article1.setDiscountRate(0.0);
        article1.name = "RELOJ EN ORO BLANCO DE 18 QUILATES Y DIAMANTES";
        article1.setDiscount(0.0);
        article1.description = "description";
        article1.setTaxRate(0.0);
        article1.quantity = 2;
        article1.image_url = "https://i.imgur.com//CZ5UPbl.jpg";
        article1.setPrice(39.93);
        article1.url = "http://www.chanel.com/es_ES/Relojeria/relojes_joyer%C3%ADa#reloj-en-oro-blanco-de-18-quilates-y-diamantes-J10211";

        Article article2 = new Article();
        article2.id = "id2";
        article2.setDiscountRate(0.0);
        article2.name = "N°5 EAU PREMIERE SPRAY";
        article2.setDiscount(0.0);
        article2.description = "description";
        article2.setTaxRate(0.0);
        article2.quantity = 1;
        article2.image_url = "https://i.imgur.com//1nIay4X.jpg";
        article2.setPrice(35.09);
        article2.url = "https://www.chanel.com/en_US/fragrance-beauty/fragrance-no5-no5-88145/sku/138083";

        Article article3 = new Article();
        article3.id = "id3";
        article3.setDiscountRate(0.0);
        article3.name = "ILLUSION D'OMBRE";
        article3.setDiscount(0.0);
        article3.description = "description";
        article3.setTaxRate(0.0);
        article3.quantity = 1;
        article3.image_url = "https://i.imgur.com//4j2ib6w.jpg";
        article3.setPrice(15.73);
        article3.url = "https://www.chanel.com/en_US/fragrance-beauty/makeup-eyeshadow-illusion-d%27ombre-122567";

        articles.add(article1);
        articles.add(article2);
        articles.add(article3);

        Order order = new Order();
        order.setTotalAmount(20.00);
        order.setCurrency(Currency.getInstance(Locale.getDefault()));
        order.setDiscount(3.62);
        order.setDiscountRate(1.0);
        order.setCartRate(1.0);
        order.setCartDiscountRate(1.0);
        order.id = String.valueOf(Math.abs(new Random().nextInt() * 100000));
        order.setTaxRate(0.21);
        order.articles = articles;

        return order;
    }
}
