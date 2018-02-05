# Aplazame Android SDK

## How to use

First at all you need to configure AplazameSDK with your public token and the environment:

```java
AplazameSDK.setConfiguration(String token, boolean debug)
```

Then you can check if Aplazame is available for your order. There are two ways to do this:

1) The first is to use only the total amount of the order and the initials of the currency
```java
AplazameSDK.checkAvailability(Double amount, String currency, AvailabilityCallback responseCallback)
```
2) The second is to use a Checkout object
```java
AplazameSDK.checkAvailability(Checkout checkout, AvailabilityCallback responseCallback)
```
Both use the **AvailabilityCallback** interface with these 3 methods:
- `onAvailabilitySuccess`: Aplazame is available
- `onAvailabilityFailure`: Aplazame is not available
- `onFailure`: Unknown error. It could be a timeout, Internet not available, so on.

Initialize the checkout in a WebView
```java
AplazameSDK.initializeAplazameWebView(WebView webView, JsWebViewEvents jsWebViewEvents);
```
**JsWebViewEvents** interface contains these 5 methods:
- `onPageStarted`: WebView event. Notify the host application that Aplazame page has started loading. 
- `onPageFinished`: WebView event. Notify the host application that Aplazame page has finished loading.
- `onReadyEvent`: Event received when the checkout is loaded in the WebView. Equivalent to the "checkout-ready" event.
- `onStatusChangeEvent (String status)`: Indicates a change of status of the checkout when it already has a result and updates the session while it remains open. Equivalent to the "status-change" event. Possible status values: "success", "pending" or "ko".
- `onCloseEvent (String status)`: Indicates that the checkout has been closed. Possible status values: "success", "pending", "dismiss" or "ko".

More information about status event: https://aplazame.com/docs/api/checkout-parameters/checkout-postmessage/

## Usage example

Check the [demo project](https://github.com/aplazame/android-sdk/tree/master/demo) to see an example of their use.

Note: We will use a complete Checkout order. For more information: https://aplazame.com/docs/api/checkout-parameters/


1) Initialize Checkout order:

```java
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
```
2) Aplazame SDK configuration:

```java
AplazameSDK.setConfiguration("my public key", true)
```
3) Check Aplazame available
```java
AplazameSDK.checkAvailability(createCheckout(), new AvailabilityCallback() {
            @Override
            public void onAvailabilitySuccess() {
                // Enable checkout button for instance
            }

            @Override
            public void onAvailabilityFailure() {
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

License
-------

Aplazame is Copyright (c) 2018 Aplazame, inc. It is free software, and may be
redistributed under the terms specified in the [LICENSE](https://raw.githubusercontent.com/aplazame/android-sdk/master/LICENSE.txt) file.

About
-----
https://aplazame.com/
