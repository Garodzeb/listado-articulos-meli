package com.grodriguez.melisearchdatasource.model.site;

import java.util.ArrayList;
import java.util.List;

public class IdentificationRule {
    private List<String> enabledTaxpayerTypes = new ArrayList<>(); // Ej.: [ 'IVA Exento', 'Monotributo', 'IVA Responsable Inscripto' ]
    private String beginsWith = "";
    private String type = ""; // Ej. "number"
    private int minLength = 0;
    private int maxLength = 0;

    public IdentificationRule() {
    }

    // region GET-SET

    public List<String> getEnabledTaxpayerTypes() {
        return enabledTaxpayerTypes;
    }

    public void setEnabledTaxpayerTypes(List<String> enabledTaxpayerTypes) {
        this.enabledTaxpayerTypes = enabledTaxpayerTypes;
    }

    public String getBeginsWith() {
        return beginsWith;
    }

    public void setBeginsWith(String beginsWith) {
        this.beginsWith = beginsWith;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    // endregion

}// End Class
