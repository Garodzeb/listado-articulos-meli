package com.grodriguez.melisearchdatasource.model.product;

import java.util.ArrayList;
import java.util.List;

public class ProductAttribute {
    private String id = "";
    private String valueId = "";
    private String valueName = "";
    private Object valueStruct = null;// TODO: ver como manejar los objetos de tipo struct
    private double source = 0;
    private String name = "";
    private List<ProductAttributeValue> values = new ArrayList<>();
    private String attributeGroupId = "";
    private String attributeGroupName =  "";

    public ProductAttribute() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValueId() {
        return valueId;
    }

    public void setValueId(String valueId) {
        this.valueId = valueId;
    }

    public String getValueName() {
        return valueName;
    }

    public void setValueName(String valueName) {
        this.valueName = valueName;
    }

    public Object getValueStruct() {
        return valueStruct;
    }

    public void setValueStruct(Object valueStruct) {
        this.valueStruct = valueStruct;
    }

    public double getSource() {
        return source;
    }

    public void setSource(double source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductAttributeValue> getValues() {
        return values;
    }

    public void setValues(List<ProductAttributeValue> values) {
        this.values = values;
    }

    public String getAttributeGroupId() {
        return attributeGroupId;
    }

    public void setAttributeGroupId(String attributeGroupId) {
        this.attributeGroupId = attributeGroupId;
    }

    public String getAttributeGroupName() {
        return attributeGroupName;
    }

    public void setAttributeGroupName(String attributeGroupName) {
        this.attributeGroupName = attributeGroupName;
    }

    // endregion

}// End Class
