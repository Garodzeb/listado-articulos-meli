package com.grodriguez.melisearchcore.model.domain.site;

public class SiteCurrency {
    private String id = "";// Ej. "ARS"
    private String symbol = "";// Ej. "USD"

    public SiteCurrency() {
    }

    public SiteCurrency(String id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // endregion

}// End Class
