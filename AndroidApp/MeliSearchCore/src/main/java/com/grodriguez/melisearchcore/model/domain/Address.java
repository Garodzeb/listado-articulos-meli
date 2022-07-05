package com.grodriguez.melisearchcore.model.domain;

public class Address {
    private String stateId = "";
    private String stateName = "";
    private String cityId = "";
    private String cityName = "";

    public Address() {
    }

    // region GET-SET

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    // endregion

}// End Class

