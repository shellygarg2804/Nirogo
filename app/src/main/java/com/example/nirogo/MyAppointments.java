package com.example.nirogo;

public class MyAppointments {

    String drName, Date, Time;

    public MyAppointments() {
    }

    public MyAppointments(String drName, String date, String time) {
        this.drName = drName;
        Date = date;
        Time = time;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
