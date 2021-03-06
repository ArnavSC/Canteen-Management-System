package com.example.canteenmanagementsystem;

public class items {

    String price,availableQuantity,totalQuantity;
    int quantity;
    String image,name;

    public items() {
    }

    public items(String name, int quantity) {
        this.quantity = quantity;
        this.name = name;
    }

    public items(String price, String availableQuantity, String totalQuantity, String image, String name) {
        this.price = price;
        this.availableQuantity = availableQuantity;
        this.totalQuantity = totalQuantity;
        this.image = image;
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
}
