package com.grodriguez.melisearchdatasource.model.product;

import com.grodriguez.melisearchdatasource.model.location.Address;
import com.grodriguez.melisearchdatasource.model.shipping.Shipping;
import com.grodriguez.melisearchdatasource.model.seller.Seller;
import com.grodriguez.melisearchdatasource.model.seller.SellerAddress;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {
    private String id = "";
    private String siteId = "";
    private String title = "";
    private Date stopTime = new Date();
    private String listingTypeId = "";
    private String condition = "";
    private String permalink = "";
    private boolean useThumbnailId = false;
    private String thumbnail = "";
    private String thumbnailId = "";
    private String categoryId = "";
    private boolean catalogListing = false;
    private String officialStoreId = "";
    private Seller seller = new Seller();
    private SellerAddress sellerAddress = new SellerAddress();
    private boolean acceptsMercadoPago = false;
    private String buyingMode = "";
    private String currencyId = "";
    private double price = 0;
    private double salePrice = 0;
    private double originalPrice = 0;
    private ProductPriceReference prices = new ProductPriceReference();
    private Object discounts = null; //TODO: validar si es un tipo de objeto complejo
    private Object melicoin = null; //TODO: validar si es un tipo de objeto complejo
    private double availableQty = 0;
    private double soldQty = 0;
    private int orderBackend = 0;
    private Address address = new Address();
    private Shipping shipping = new Shipping();
    private String domainId = "";
    private String catalogProductId = "";
    private ProductInstallment installments = new ProductInstallment();
    private List<ProductAttribute> attributes = new ArrayList<>();
    private String offerScore = "";
    private String offerShare = "";
    private String matchScore = "";
    private String winnerItemId = "";
    private List<String> tags = new ArrayList<>();

    public Product() {
    }

    // region GET-SET

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getListingTypeId() {
        return listingTypeId;
    }

    public void setListingTypeId(String listingTypeId) {
        this.listingTypeId = listingTypeId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public boolean isUseThumbnailId() {
        return useThumbnailId;
    }

    public void setUseThumbnailId(boolean useThumbnailId) {
        this.useThumbnailId = useThumbnailId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isCatalogListing() {
        return catalogListing;
    }

    public void setCatalogListing(boolean catalogListing) {
        this.catalogListing = catalogListing;
    }

    public String getOfficialStoreId() {
        return officialStoreId;
    }

    public void setOfficialStoreId(String officialStoreId) {
        this.officialStoreId = officialStoreId;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public SellerAddress getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(SellerAddress sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public boolean isAcceptsMercadoPago() {
        return acceptsMercadoPago;
    }

    public void setAcceptsMercadoPago(boolean acceptsMercadoPago) {
        this.acceptsMercadoPago = acceptsMercadoPago;
    }

    public String getBuyingMode() {
        return buyingMode;
    }

    public void setBuyingMode(String buyingMode) {
        this.buyingMode = buyingMode;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public ProductPriceReference getPrices() {
        return prices;
    }

    public void setPrices(ProductPriceReference prices) {
        this.prices = prices;
    }

    public Object getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Object discounts) {
        this.discounts = discounts;
    }

    public Object getMelicoin() {
        return melicoin;
    }

    public void setMelicoin(Object melicoin) {
        this.melicoin = melicoin;
    }

    public double getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(double availableQty) {
        this.availableQty = availableQty;
    }

    public double getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(double soldQty) {
        this.soldQty = soldQty;
    }

    public int getOrderBackend() {
        return orderBackend;
    }

    public void setOrderBackend(int orderBackend) {
        this.orderBackend = orderBackend;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
    }

    public ProductInstallment getInstallments() {
        return installments;
    }

    public void setInstallments(ProductInstallment installments) {
        this.installments = installments;
    }

    public List<ProductAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ProductAttribute> attributes) {
        this.attributes = attributes;
    }

    public String getOfferScore() {
        return offerScore;
    }

    public void setOfferScore(String offerScore) {
        this.offerScore = offerScore;
    }

    public String getOfferShare() {
        return offerShare;
    }

    public void setOfferShare(String offerShare) {
        this.offerShare = offerShare;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public String getWinnerItemId() {
        return winnerItemId;
    }

    public void setWinnerItemId(String winnerItemId) {
        this.winnerItemId = winnerItemId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    // endregion

}// End Class
