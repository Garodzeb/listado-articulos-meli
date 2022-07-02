package com.grodriguez.melisearchdatasource.model.seller;

import java.util.ArrayList;
import java.util.List;

public class SellerEshop {
    private String sellerId = "";
    private String eshopRubro = "";
    private String eshopId = "";
    private String nickname = "";
    private String siteId = "";
    private String eshopLogoUrl = "";
    private String eshopStatusId = "";
    private String eshopExperience = "";
    private List<String> eshopLocations = new ArrayList<>();

    public SellerEshop() {
    }

    // region GET-SET

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getEshopRubro() {
        return eshopRubro;
    }

    public void setEshopRubro(String eshopRubro) {
        this.eshopRubro = eshopRubro;
    }

    public String getEshopId() {
        return eshopId;
    }

    public void setEshopId(String eshopId) {
        this.eshopId = eshopId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getEshopLogoUrl() {
        return eshopLogoUrl;
    }

    public void setEshopLogoUrl(String eshopLogoUrl) {
        this.eshopLogoUrl = eshopLogoUrl;
    }

    public String getEshopStatusId() {
        return eshopStatusId;
    }

    public void setEshopStatusId(String eshopStatusId) {
        this.eshopStatusId = eshopStatusId;
    }

    public String getEshopExperience() {
        return eshopExperience;
    }

    public void setEshopExperience(String eshopExperience) {
        this.eshopExperience = eshopExperience;
    }

    public List<String> getEshopLocations() {
        return eshopLocations;
    }

    public void setEshopLocations(List<String> eshopLocations) {
        this.eshopLocations = eshopLocations;
    }

    // endregion

}// End Class
