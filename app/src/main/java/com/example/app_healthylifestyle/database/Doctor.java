package com.example.app_healthylifestyle.database;

public class Doctor {
    String name,id,chuyenkhoa,kinhnghiem,benhvien,chitiet;

    public Doctor() {
    }

    public Doctor(String name, String id, String chuyenkhoa, String kinhnghiem, String benhvien, String chitiet) {
        this.name = name;
        this.id = id;
        this.chuyenkhoa = chuyenkhoa;
        this.kinhnghiem = kinhnghiem;
        this.benhvien = benhvien;
        this.chitiet = chitiet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChuyenkhoa() {
        return chuyenkhoa;
    }

    public void setChuyenkhoa(String chuyenkhoa) {
        this.chuyenkhoa = chuyenkhoa;
    }

    public String getKinhnghiem() {
        return kinhnghiem;
    }

    public void setKinhnghiem(String kinhnghiem) {
        this.kinhnghiem = kinhnghiem;
    }

    public String getBenhvien() {
        return benhvien;
    }

    public void setBenhvien(String benhvien) {
        this.benhvien = benhvien;
    }

    public String getChitiet() {
        return chitiet;
    }

    public void setChitiet(String chitiet) {
        this.chitiet = chitiet;
    }
}
