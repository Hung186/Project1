package com.firstapp.duan1.model;

public class Product {
    public String __id, productCategoryId, productName, productBrand, productDescription;
    public double productPrice;
    public String[] productThumbnails;

    public Product() {}

    public Product(String id, String productCategoryId, String productName, String productBrand, String productDescription, double productPrice, String[] productThumbnails) {
        this.__id = id;
        this.productCategoryId = productCategoryId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productThumbnails = productThumbnails;
    }
}
