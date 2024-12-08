package com.example.app_healthylifestyle.database;
public class Users {
    public String name;
    public String phone;
    public String email;
    public String date;
    public String gender;
    public String address;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users() {
    }

    public Users(String name, String phone, String email, String date, String gender,String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.gender = gender;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

