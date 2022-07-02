package com.grodriguez.melisearchdatasource.model.seller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Seller {
    private String id = "";
    private String permalink = "";
    private Date registrationDate = new Date();
    private boolean carDealer = false;
    private boolean realEstateAgency = false;
    private List<String> tags = new ArrayList<>();
    private SellerEshop Sellereshop = new SellerEshop();
    private SellerReputation reputation = new SellerReputation();

    public Seller() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isCarDealer() {
        return carDealer;
    }

    public void setCarDealer(boolean carDealer) {
        this.carDealer = carDealer;
    }

    public boolean isRealEstateAgency() {
        return realEstateAgency;
    }

    public void setRealEstateAgency(boolean realEstateAgency) {
        this.realEstateAgency = realEstateAgency;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public SellerEshop getSellereshop() {
        return Sellereshop;
    }

    public void setSellereshop(SellerEshop sellereshop) {
        Sellereshop = sellereshop;
    }

    public SellerReputation getReputation() {
        return reputation;
    }

    public void setReputation(SellerReputation reputation) {
        this.reputation = reputation;
    }


    // endregion

}// End Class
