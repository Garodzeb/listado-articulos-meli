package com.grodriguez.melisearchdatasource.model.site;

import java.util.ArrayList;
import java.util.List;

public class SiteSetting {
    private List<String> identificationTypes = new ArrayList<>();
    private List<String> taxPayerTypes = new ArrayList<>();
    private List<IdentificationTypeRule> identificationTypesRules = new ArrayList<>();

    public SiteSetting() {
    }

    // region GET-SET

    public List<String> getIdentificationTypes() {
        return identificationTypes;
    }

    public void setIdentificationTypes(List<String> identificationTypes) {
        this.identificationTypes = identificationTypes;
    }

    public List<String> getTaxPayerTypes() {
        return taxPayerTypes;
    }

    public void setTaxPayerTypes(List<String> taxPayerTypes) {
        this.taxPayerTypes = taxPayerTypes;
    }

    public List<IdentificationTypeRule> getIdentificationTypesRules() {
        return identificationTypesRules;
    }

    public void setIdentificationTypesRules(List<IdentificationTypeRule> identificationTypesRules) {
        this.identificationTypesRules = identificationTypesRules;
    }

    // endregion

}// End Class
