package com.example.nailzbynut;

public class ServiceModel {
    private String serviceName;
    private String serviceDesc;
    private String price;
    private int imageResId;

    // KONSTRUKTOR
    public ServiceModel(String serviceName, String serviceDesc, String price, int imageResId) {
        this.serviceName = serviceName;
        this.serviceDesc = serviceDesc;
        this.price = price;
        this.imageResId = imageResId;
    }

    // GETTER
    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}