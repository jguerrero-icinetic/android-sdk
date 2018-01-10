package com.aplazame.sdk.model;

public class Merchant {

    /**
     (Sólo aplica si la API de confirmación está configurada) Establece el tiempo máximo, en minutos, del que dispone el usuario para completar el proceso de checkout. (por omisión 2880; mín. 1; máx. 2880 )
     Default: 2880
     */
    private final Long timeoutCheckout;

    /**
     (Sólo aplica si la API de confirmación está configurada) Indica si el resultado de la solicitud de crédito aprobada por Aplazame debe completarse informando al usuario en el proceso de checkout
     Default: true
     */
    private final boolean confirmOnCheckout;

    /*
     *  Optionals
     */

    /**
     (Optional) Indica si el checkout se redirige automáticamente a success_url en caso de éxito. ( por omisión: false )
     Default: false
     */
    private final boolean closeOnSuccess;
    /**
     (Optional) (Sólo aplica si la API de confirmación está configurada) Establece el tiempo adicional, en minutos, del que dispone el usuario para completar la validación de identidad una vez finalizado el proceso de checkout. ( por omisión 2880 si confirm_on_checkout está deshabilitado, 0 en caso contrario; mín. 0; máx. 2880 )
     Default: 2880
     */
    private final Long timeoutExtra;

    public Merchant(Long timeoutCheckout, Long timeoutExtra, boolean confirmOnCheckout, boolean closeOnSuccess) {
        this.timeoutCheckout = timeoutCheckout;
        this.timeoutExtra = timeoutExtra;
        this.confirmOnCheckout = confirmOnCheckout;
        this.closeOnSuccess = closeOnSuccess;
    }

    public Long getTimeoutCheckout() {
        return timeoutCheckout;
    }

    public Long getTimeoutExtra() {
        return timeoutExtra;
    }

    public boolean isConfirmOnCheckout() {
        return confirmOnCheckout;
    }

    public boolean isCloseOnSuccess() {
        return closeOnSuccess;
    }
}
