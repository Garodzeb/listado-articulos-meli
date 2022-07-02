package com.grodriguez.melisearchdatasource.model.site;

import java.util.ArrayList;
import java.util.List;

public class IdentificationTypeRule {
    private String identificationType = "";
    private List<IdentificationRule> rules = new ArrayList<>();

    public IdentificationTypeRule() {
    }

    // region GET-SET

    public String getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

    public List<IdentificationRule> getRules() {
        return rules;
    }

    public void setRules(List<IdentificationRule> rules) {
        this.rules = rules;
    }

    // endregion

}// End Class
