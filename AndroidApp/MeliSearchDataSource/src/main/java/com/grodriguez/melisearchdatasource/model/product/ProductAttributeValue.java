package com.grodriguez.melisearchdatasource.model.product;

public class ProductAttributeValue {
    private String id = "";
    private String name = "";
    private Object struct = null;// TODO: ver como manejar los objetos de tipo struct
    private double source = 0;

    public ProductAttributeValue() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getStruct() {
        return struct;
    }

    public void setStruct(Object struct) {
        this.struct = struct;
    }

    public double getSource() {
        return source;
    }

    public void setSource(double source) {
        this.source = source;
    }

    // endregion

}// End Class
