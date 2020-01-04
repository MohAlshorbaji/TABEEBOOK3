package com.example.tabeebook.models;

public class Pharmacy {

    private String name;
    private String pharmacyUserID;
    private String pharmacyEmail;
    private String address;
    private String phoneNumber;
    private String opeiningHours;
    private String type;
    private String owner;
    private String image;

    public Pharmacy(String name, String pharmacyUserID, String pharmacyEmail, String address, String phoneNumber, String opeiningHours, String type, String owner, String image) {
        this.name = name;
        this.pharmacyUserID = pharmacyUserID;
        this.pharmacyEmail = pharmacyEmail;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.opeiningHours = opeiningHours;
        this.type = type;
        this.owner = owner;
        this.image = image;
    }

    public Pharmacy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharmacyUserID() {
        return pharmacyUserID;
    }

    public void setPharmacyUserID(String pharmacyUserID) {
        this.pharmacyUserID = pharmacyUserID;
    }

    public String getPharmacyEmail() {
        return pharmacyEmail;
    }

    public void setPharmacyEmail(String pharmacyEmail) {
        this.pharmacyEmail = pharmacyEmail;
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
