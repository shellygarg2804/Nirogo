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

    public DocUploadInfo() {
    }

    public DocUploadInfo(String name, String speciality , String age, String city, String url) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.speciality = speciality;
        this.imageURL= url;
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