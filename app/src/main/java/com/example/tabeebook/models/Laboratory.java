package com.example.tabeebook.models;

public class Laboratory {

    private String name;
    private String laboratoryUserID;
    private String laboratoryEmail;
    private String address;
    private String phoneNumber;
    private String opeiningHours;
    private String image;

    public Laboratory() {
        this.name = name;
        this.laboratoryUserID = laboratoryUserID;
        this.laboratoryEmail = laboratoryEmail;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.opeiningHours = opeiningHours;
        this.image = image;
    }

    public Laboratory(String name, String laboratoryUserID, String laboratoryEmail, String address, String phoneNumber, String opeiningHours, String type, String owner, String image) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLaboratoryUserID() {
        return laboratoryUserID;
    }

    public void setLaboratoryUserID(String laboratoryUserID) {
        this.laboratoryUserID = laboratoryUserID;
    }

    public String getLaboratoryEmail() {
        return laboratoryEmail;
    }

    public void setLaboratoryEmail(String laboratoryEmail) {
        this.laboratoryEmail = laboratoryEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOpeiningHours() {
        return opeiningHours;
    }

    public void setOpeiningHours(String opeiningHours) {
        this.opeiningHours = opeiningHours;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
