package com.example.nirogo.NearbyDoctors;

public class UploadInfo {


    String url, name, city, speciality;

    public UploadInfo() {
    }

    public UploadInfo(String url, String name, String speciality, String city) {
        this.url = url;
        this.name = name;
        this.city = city;
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
