package com.grodriguez.melichallenge.framework.network.retrofit.apis.site.response;

import com.google.gson.annotations.SerializedName;

public class CurrencyGSonResponseEntity {

    @SerializedName("id")
    private String id;

    @SerializedName("symbol")
    private String symbol;

    public CurrencyGSonResponseEntity() {
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
