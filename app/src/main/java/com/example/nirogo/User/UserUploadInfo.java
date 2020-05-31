package com.example.nirogo.User;

public class UserUploadInfo {

    public String imageName;
    public String imageURL;
    public String age;
    public String city;
    public String type;

    public UserUploadInfo(String type, String name, String url, String age, String city) {
        this.type = type;
        this.imageName = name;
        this.imageURL= url;
        this.age = age;
        this.city = city;
    }

    public String getType() {
        return type;
    }

    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

}