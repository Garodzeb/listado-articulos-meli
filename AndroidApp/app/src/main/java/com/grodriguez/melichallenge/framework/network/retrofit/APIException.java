package com.grodriguez.melichallenge.framework.network.retrofit;

import java.util.ArrayList;
import java.util.List;

// Clase de ayuda que se encarga de absorver los errores provenientes de las APIs
public class APIException extends Exception {

    private int responseCode = 0;
    private String statusCodeDescription = "";
    private String errorMessage = "";
    private List<String> errorCause = new ArrayList<>();

    // region GET-SET

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
        statusCodeDescription = getStatusCodeDescription(this.responseCode);
    }

    public String getStatusCodeDescription() {
        return statusCodeDescription;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(List<String> errorCause) {
        this.errorCause = errorCause;
    }

    // endregion

    private static String getStatusCodeDescription(int statusCode) {
        String description = "Error Code: " + Integer.toString(statusCode);

        switch (statusCode) {
            case 400:
                description = "400 - Bad Request";
                break;
            case 401:
                description = "401 - Unauthorized";
                break;
            case 403:
                description = "403 - Forbidden";
                break;
            case 404:
                description = "404 - Not Found";
                break;
            case 405:
                description = "405 - Method Not Allowed";
                break;
            case 406:
                description = "406 - Not Acceptable";
                break;
            case 407:
                description = "407 - Proxy Authentication Required";
                break;
            case 408:
                description = "408 - Request Timeout";
                break;
            case 409:
                description = "409 - Conflict";
                break;
            case 410:
                description = "410 - Gone";
                break;
            case 500:
                description = "500 - Internal Server Error";
                break;
            case 501:
                description = "501 - Not Implemented";
                break;
            case 502:
                description = "502 - Bad Gateway";
                break;
            case 503:
                description = "503 - Service Unavailable";
                break;
            case 504:
                description = "504 - Gateway Timeout";
                break;
            case 505:
                description = "505 - HTTP Version Not Supported";
                break;
            case 511:
                description = "511 - Network Authentication Required";
                break;
        }

        return description;
    }

}// End Class
