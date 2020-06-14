package com.example.nirogo.Doctor;


public class DocUploadInfo {

    public String name;
    public String id;
    public String type;
    public String docImage;
    public String imageURL;
    public String age;
    public String city;
    public String speciality;

    public DocUploadInfo() {
    }

    public DocUploadInfo(String name, String docUrl, String age, String city, String speciality) {
        this.name = name;
        this.imageURL = docUrl;
        this.age = age;
        this.city = city;
        this.speciality = speciality;
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

    public String getDocImage() {
        return docImage;
    }

    public String getImageURL() {
        return imageURL;
    }

}