package com.example.nailzbynut; // Sesuaikan dengan nama package proyekmu

public class NailModel {
    private String name;
    private String price;
    private int imageResourceId;

    // Constructor
    public NailModel(String name, String price, int imageResourceId) {
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    // Getter
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}