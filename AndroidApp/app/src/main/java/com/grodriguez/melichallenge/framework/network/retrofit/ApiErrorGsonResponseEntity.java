package com.grodriguez.melichallenge.framework.network.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiErrorGsonResponseEntity {

    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private String error;

    @SerializedName("status")
    private int status;

    @SerializedName("cause")
    private List<String> cuase;

    public ApiErrorGsonResponseEntity() {
    }

    // region GET-SET

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getCuase() {
        return cuase;
    }

    public void setCuase(List<String> cuase) {
        this.cuase = cuase;
    }

    // endregion

}// End Class
