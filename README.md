# Aplazame Android SDK

## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    compile 'com.github.aplazame:android-sdk:master-SNAPSHOT'
}
```

## How to use

First at all you need to configure AplazameSDK with your public token and the environment:

```java
AplazameSDK.setConfiguration(String token, boolean debug)
```

Then you can check if Aplazame is available for your order:

```java
AplazameSDK.checkAvailability(Double amount, String currency, AvailabilityCallback responseCallback)
```
The **AvailabilityCallback** contains the following 3 methods:
- `onAvailable`: Aplazame is available
- `onNotAvailable`: Aplazame is not available
- `onFailure`: Unknown error. It could be a timeout, Internet not available, so on.

Initialize the checkout in a WebView
```java
AplazameSDK.initializeAplazameWebView(Activity activity, WebView webView, JsWebViewEvents jsWebViewEvents);
```
**JsWebViewEvents** interface contains these 5 methods:
- `onPageStarted`: WebView event. Notify the host application that Aplazame page has started loading. 
- `onPageFinished`: WebView event. Notify the host application that Aplazame page has finished loading.
- `onReadyEvent`: Event received when the checkout is loaded in the WebView. Equivalent to the "checkout-ready" event.
- `onStatusChangeEvent (String status)`: Indicates a change of status of the checkout when it already has a result and updates the session while it remains open. Equivalent to the "status-change" event. Possible status values: "success", "pending" or "ko".
- `onCloseEvent (String status)`: Indicates that the checkout has been closed. Possible status values: "success", "pending", "dismiss" or "ko".

Add AplazameSDK in your onActivityResult

```java
AplazameSDK.onActivityResult(requestCode, resultCode, intent);
```

Add AplazameSDK in your onRequestPermissionsResult. There are 3 options:

- A toast with a default message if you don't accept the app permissions
```java
AplazameSDK.onActivityResult(requestCode, resultCode, intent);
```

- A toast with a custom message if you don't accept the app permissions
```java
AplazameSDK.onActivityResult(requestCode, resultCode, intent, String);
```

- A listener to customize your response. The methods are onAcceptPermissions() and onDeclinePermissions().
```java
AplazameSDK.onActivityResult(requestCode, resultCode, intent, OnRequestPermissionsListener);
```

More information about status event: https://aplazame.com/docs/api/checkout-parameters/checkout-postmessage/

## Usage example

Check the [demo project](https://github.com/aplazame/android-sdk/tree/master/demo) to see an example of their use.

Note: We will use a complete Checkout order. For more information: https://aplazame.com/docs/api/checkout-parameters/


1) Initialize Checkout order:

```java
private Checkout createCheckout() {
    Checkout checkout = new Checkout();
    checkout.toc = true;
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
    billing.country = "ES";
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
    address.country = "ES";
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
    customer.date_joined = MapperUtils.dateToString(new Date(System.currentTimeMillis()));
    customer.birthday = MapperUtils.dateBirthdayToString(dateBirthday);
    customer.last_login = MapperUtils.dateToString(new Date(System.currentTimeMillis()));
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
    shipping.country = "ES";
    shipping.phone = "555555555";
    shipping.alt_phone = "666666666";
    shipping.first_name = "Fernando";
    shipping.last_name = "Cabello";
    shipping.name = "Fernando Envío";
    shipping.price = MapperUtils.doubleToDecimal(10.0);
    shipping.tax_rate = MapperUtils.doubleToDecimal(0.21);
    shipping.discount = MapperUtils.doubleToDecimal(1.0);
    shipping.discount_rate = MapperUtils.doubleToDecimal(1.0);

    return shipping;
}

private Order createOrder() {
    List<Article> articles = new ArrayList<>();

    Article article1 = new Article();
    article1.id = "id1";
    article1.discount_rate = MapperUtils.doubleToDecimal(0.0);
    article1.name = "RELOJ EN ORO BLANCO DE 18 QUILATES Y DIAMANTES";
    article1.discount = MapperUtils.doubleToDecimal(0.0);
    article1.description = "description";
    article1.tax_rate = MapperUtils.doubleToDecimal(0.0);
    article1.quantity = 2;
    article1.image_url = "https://i.imgur.com//CZ5UPbl.jpg";
    article1.price = MapperUtils.doubleToDecimal(39.93);
    article1.url = "http://www.chanel.com/es_ES/Relojeria/relojes_joyer%C3%ADa#reloj-en-oro-blanco-de-18-quilates-y-diamantes-J10211";

    Article article2 = new Article();
    article2.id = "id2";
    article2.discount_rate = MapperUtils.doubleToDecimal(0.0);
    article2.name = "N°5 EAU PREMIERE SPRAY";
    article2.discount = MapperUtils.doubleToDecimal(0.0);
    article2.description = "description";
    article2.tax_rate = MapperUtils.doubleToDecimal(0.0);
    article2.quantity = 1;
    article2.image_url = "https://i.imgur.com//1nIay4X.jpg";
    article2.price = MapperUtils.doubleToDecimal(35.09);
    article2.url = "https://www.chanel.com/en_US/fragrance-beauty/fragrance-no5-no5-88145/sku/138083";

    Article article3 = new Article();
    article3.id = "id3";
    article3.discount_rate = MapperUtils.doubleToDecimal(0.0);
    article3.name = "ILLUSION D'OMBRE";
    article3.discount = MapperUtils.doubleToDecimal(0.0);
    article3.description = "description";
    article3.tax_rate = MapperUtils.doubleToDecimal(0.0);
    article3.quantity = 1;
    article3.image_url = "https://i.imgur.com//4j2ib6w.jpg";
    article3.price = MapperUtils.doubleToDecimal(15.73);
    article3.url = "https://www.chanel.com/en_US/fragrance-beauty/makeup-eyeshadow-illusion-d%27ombre-122567";

    articles.add(article1);
    articles.add(article2);
    articles.add(article3);

    Order order = new Order();
    order.total_amount = MapperUtils.doubleToDecimal(132.06);
    order.currency = "EUR";
    order.discount = MapperUtils.doubleToDecimal(3.62);
    order.discount_rate = MapperUtils.doubleToDecimal(1.0);
    order.cart_rate = MapperUtils.doubleToDecimal(1.0);
    order.discount_rate = MapperUtils.doubleToDecimal(1.0);
    order.id = String.valueOf(Math.abs(new Random().nextInt() * 100000));
    order.tax_rate = MapperUtils.doubleToDecimal(0.21);
    order.articles = articles;

    return order;
}
```
2) Aplazame SDK configuration:

```java
AplazameSDK.setConfiguration("my public key", true)
```
3) Check Aplazame available
```java
AplazameSDK.checkAvailability(132.06, "EUR", new AvailabilityCallback() {
    @Override
    public void onAvailable() {
        // Enable checkout button for instance
    }

    @Override
    public void onNotAvailable() {
        // Hide the checkout button for instance
    }

    @Override
    public void onFailure() {
        // Hide the checkout button for instance (unknown error)
    }
});
```


![alt text](https://raw.githubusercontent.com/aplazame/android-sdk/master/image1.png)

4) Initialize WebView

```java
AplazameSDK.initializeAplazameWebView(webView, new JsWebViewEvents() {
    @Override
    public void onPageStarted() {}

    @Override
    public void onPageFinished() {}

    @Override
    public void onReadyEvent() {}

    @Override
    public void onStatusChangeEvent(String status) {
        switch (status) {
            case SUCCESS:
                break;
            case PENDING:
                break;
            case KO:
                break;
        }
    }

    @Override
    public void onCloseEvent(String status) {
        switch (status) {
            case SUCCESS:
                break;
            case PENDING:
                finish();
                break;
            case DISMISS:
                finish();
                break;
            case KO:
                break;
        }
    }
});
```

![alt text](https://raw.githubusercontent.com/aplazame/android-sdk/master/image2.png)

5) Add Aplazame SDK in onActivityResult

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    AplazameSDK.onActivityResult(requestCode, resultCode, intent);
}
```

6) Add Aplazame SDK in onRequestPermissionsResult

```java
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    super.onActivityResult(requestCode, resultCode, intent);
    AplazameSDK.onRequestPermissionsResult(this, requestCode, grantResults);
}
```

License
-------

Aplazame is Copyright (c) 2018 Aplazame, inc. It is free software, and may be
redistributed under the terms specified in the [LICENSE](/LICENSE.txt) file.

About
-----
https://aplazame.com/
