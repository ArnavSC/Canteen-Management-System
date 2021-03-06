package com.example.canteenmanagementsystem;

public class cartItems {
    String quantity,image,name,price;

    public cartItems(String quantity, String image, String name, String price) {
        this.quantity = quantity;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public cartItems() {
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
