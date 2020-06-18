package com.example.nirogo;

public class AdminShow {

    String docName, userName, dateApt, timeApt, phoneUser;

    public AdminShow() {
    }

    public AdminShow(String docName, String userName, String dateApt, String timeApt, String phoneUser) {
        this.docName = docName;
        this.userName = userName;
        this.dateApt = dateApt;
        this.timeApt = timeApt;
        this.phoneUser = phoneUser;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateApt() {
        return dateApt;
    }

    public void setDateApt(String dateApt) {
        this.dateApt = dateApt;
    }

    public String getTimeApt() {
        return timeApt;
    }

    public void setTimeApt(String timeApt) {
        this.timeApt = timeApt;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }
}
