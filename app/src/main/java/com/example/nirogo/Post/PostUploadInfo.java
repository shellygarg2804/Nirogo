package com.example.nirogo.Post;

public class PostUploadInfo {

    String docName, speciality, time;
    String desc;

    public PostUploadInfo(String docName, String speciality, String time, String desc) {
        this.docName = docName;
        this.speciality = speciality;
        this.time = time;
        this.desc = desc;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
