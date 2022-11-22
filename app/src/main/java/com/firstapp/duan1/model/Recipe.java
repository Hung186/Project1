package com.firstapp.duan1.model;

public class Recipe {
    public String __id, recipeUserId, recipeStatus, recipeDate;
    public double recipeTotalPrice;

    public Recipe() {}

    public Recipe(String id, String recipeUserId, String recipeStatus, String recipeDate, double recipeTotalPrice) {
        this.__id = id;
        this.recipeUserId = recipeUserId;
        this.recipeStatus = recipeStatus;
        this.recipeDate = recipeDate;
        this.recipeTotalPrice = recipeTotalPrice;
    }
}
