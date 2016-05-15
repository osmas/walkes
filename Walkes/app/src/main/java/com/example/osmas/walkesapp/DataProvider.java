package com.example.osmas.walkesapp;

/**
 * Created by osmas on 07/01/2016.
 */
public class DataProvider {

    private String date;
    private String start_time;
    private String appointment_type;
    private String name;
    private String address;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getAppointment_type() {
        return appointment_type;
    }

    public void setAppointment_type(String appointment_type) {
        this.appointment_type = appointment_type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DataProvider(String date, String start_time, String appointment_type, String name, String address)
    {
        this.date = date;
        this.start_time = start_time;
        this.appointment_type = appointment_type;
        this.name = name;
        this.address = address;


    }
}
