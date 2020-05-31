package com.example.nirogo.Supplier;

public class SupplierUploadInfo {

    public String type;
    public String imageName;
    public String imageURL;
    public String age;
    public String city;
    public String service;

    public SupplierUploadInfo(String type, String name, String url, String age, String city, String service) {
        this.type = type;
        this.imageName = name;
        this.imageURL= url;
        this.age = age;
        this.city = city;
        this.service = service;
    }


    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type;
    }

    public String getService() {
        return service;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

}