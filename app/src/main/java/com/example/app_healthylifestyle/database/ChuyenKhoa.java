package com.example.app_healthylifestyle.database;

public class ChuyenKhoa {
    String id,name;

    public ChuyenKhoa() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChuyenKhoa(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
