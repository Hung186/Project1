package com.firstapp.duan1.model;

public class SanPham {
    public String __id, categoryId, productName, productBrand, productDescription;
    public double productPrice;
    public String[] productThumbnails;

    public SanPham() {}

    public SanPham(String id, String categoryId, String productName, String productBrand, String productDescription, double productPrice, String[] productThumbnails) {
        this.__id = id;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productThumbnails = productThumbnails;
    }
}
