package com.example.nirogo.Doctor;

import android.net.Uri;

import com.google.android.gms.tasks.Task;

public class DocUploadInfo {

    public String id;
    public String type;
    public String imageName;
    public String imageURL;
    public String age;
    public String city;
    public String speciality;

    public DocUploadInfo(String id, String type, String name, String url, String age, String city, String speciality) {
        this.id = id;
        this.type = type;
        this.imageName = name;
        this.imageURL= url;
        this.age = age;
        this.city = city;
        this.speciality = speciality;
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