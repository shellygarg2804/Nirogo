package com.example.nirogo.Doctor;

import android.net.Uri;

import com.google.android.gms.tasks.Task;


public class DocUploadInfo {

    public String name;
    public String id;
    public String type;
    public String imageName;
    public String imageURL;
    public String age;
    public String city;
    public String speciality;
    public String phone;

    public DocUploadInfo() {
    }

    public DocUploadInfo(String id, String phone, String name, String speciality , String age, String city, String url) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.city = city;
        this.speciality = speciality;
        this.imageURL= url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }


    public String getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

}