package com.example.nirogo.Adapters.appointments;

public class appointmentItem {
    private String name;
    private String speciality;
    private String distance;

    public appointmentItem(String name, String speciality, String distance){
        this.name=name;
        this.speciality=speciality;
        this.distance=distance;
    }

    public String getName() {
        return name;
    }

    public String getDistance() {
        return distance;
    }

    public String getSpeciality() {
        return speciality;
    }
}
