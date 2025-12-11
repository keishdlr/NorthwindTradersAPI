package com.pluralsight.NorthwindTradersAPI.models;

public class Category {

    //properties
    public int categoryID;
    public String categoryName;

    //empty constructor

    public Category() {
    }

    //getters and setters

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
