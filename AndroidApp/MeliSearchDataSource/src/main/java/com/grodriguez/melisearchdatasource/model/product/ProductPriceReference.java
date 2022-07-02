package com.grodriguez.melisearchdatasource.model.product;

import com.grodriguez.melisearchdatasource.model.price.PricePresentation;

import java.util.ArrayList;
import java.util.List;

public class ProductPriceReference {
    private String id = "";
    private PricePresentation presentation = new PricePresentation();
    private List<ProductPrice> prices = new ArrayList<>();
    private List<ProductPrice> paymentMethodPrices = new ArrayList<>();//TODO: validar si sigue la misma estructura que el resto de los precios
    private List<ProductPrice> referencePrices = new ArrayList<>();
    private List<Object> purchaseDiscounts = new ArrayList<>(); //TODO: validar si devuelve un objeto complejo

    public ProductPriceReference() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PricePresentation getPresentation() {
        return presentation;
    }

    public void setPresentation(PricePresentation presentation) {
        this.presentation = presentation;
    }

    public List<ProductPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<ProductPrice> prices) {
        this.prices = prices;
    }

    public List<ProductPrice> getPaymentMethodPrices() {
        return paymentMethodPrices;
    }

    public void setPaymentMethodPrices(List<ProductPrice> paymentMethodPrices) {
        this.paymentMethodPrices = paymentMethodPrices;
    }

    public List<ProductPrice> getReferencePrices() {
        return referencePrices;
    }

    public void setReferencePrices(List<ProductPrice> referencePrices) {
        this.referencePrices = referencePrices;
    }

    public List<Object> getPurchaseDiscounts() {
        return purchaseDiscounts;
    }

    public void setPurchaseDiscounts(List<Object> purchaseDiscounts) {
        this.purchaseDiscounts = purchaseDiscounts;
    }

    // endregion

}// End Class
