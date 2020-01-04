package com.example.tabeebook.models;

public class Clinic {

    private String name;
    private String clinicUserID;
    private String clinicEmailID;
    private String address;
    private String phoneNumber;
    private String opeiningHours;
    private String type;
    private String owner;
    private String image;

    public Clinic(String name, String clinicUserID, String clinicEmailID, String address, String phoneNumber, String opeiningHours, String type, String owner, String image) {
        this.name = name;
        this.clinicUserID = clinicUserID;
        this.clinicEmailID = clinicEmailID;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.opeiningHours = opeiningHours;
        this.type = type;
        this.owner = owner;
        this.image = image;
    }

    public Clinic() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClinicUserID() {
        return clinicUserID;
    }

    public void setClinicUserID(String clinicUserID) {
        this.clinicUserID = clinicUserID;
    }

    public String getClinicEmailID() {
        return clinicEmailID;
    }

    public void setClinicEmailID(String clinicEmailID) {
        this.clinicEmailID = clinicEmailID;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
