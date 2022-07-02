package com.grodriguez.melisearchdatasource.model.seller;

import com.grodriguez.melisearchdatasource.model.location.AddressCity;
import com.grodriguez.melisearchdatasource.model.location.AddressCountry;
import com.grodriguez.melisearchdatasource.model.location.AddressState;

public class SellerAddress {
    private String id = "";
    private String comment = "";
    private String addressLine = "";
    private String zipCode = "";
    private AddressCountry country = new AddressCountry();
    private AddressState state = new AddressState();
    private AddressCity city = new AddressCity();
    private String latitude = "";
    private String longitude = "";

    public SellerAddress() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public AddressCountry getCountry() {
        return country;
    }

    public void setCountry(AddressCountry country) {
        this.country = country;
    }

    public AddressState getState() {
        return state;
    }

    public void setState(AddressState state) {
        this.state = state;
    }

    public AddressCity getCity() {
        return city;
    }

    public void setCity(AddressCity city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    // endregion

}// End Class
