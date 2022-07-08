package com.grodriguez.melisearchcore.model.domain;

// TODO: reemplazar el resultado de buscar un artículo de SearchItemDTO a SearchItem 
public class SearchItem {

    private String id = "";
    private String title = "";
    private String currencyId = "";
    private SiteCurrency currency = new SiteCurrency();
    private Shipping shipping = new Shipping();
    private String categoryId = "";
    private String condition = "";
    private double price = 0;
    private String ThumbnailUrl = "";
    private boolean isFavorite = false;

    public SearchItem() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public SiteCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(SiteCurrency currency) {
        this.currency = currency;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getThumbnailUrl() {
        return ThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        ThumbnailUrl = thumbnailUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    // endregion

}// End Class
