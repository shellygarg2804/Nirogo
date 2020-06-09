package com.example.nirogo.User;

public class UserUploadInfo {

    public String name;
    public String age;
    public String city;
    public String imageName;

    public UserUploadInfo(String name, String age, String city, String imageName) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}