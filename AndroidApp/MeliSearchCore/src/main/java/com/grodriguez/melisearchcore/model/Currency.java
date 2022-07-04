package com.grodriguez.melisearchcore.model;

public class Currency {
    private String id = "";// Ej. "ARS"
    private String symbol = "";// Ej. "USD"

    public Currency() {
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
