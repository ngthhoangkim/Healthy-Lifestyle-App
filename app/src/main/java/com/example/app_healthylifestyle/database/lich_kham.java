package com.example.app_healthylifestyle.database;

public class lich_kham {
    private String tenBacSi;
    private String tenBenhVien;
    private String chuyenKhoa;

    public lich_kham() {
        // Default constructor required for calls to DataSnapshot.getValue(lich_kham.class)
    }

    public lich_kham(String tenBacSi, String tenBenhVien, String chuyenKhoa) {
        this.tenBacSi = tenBacSi;
        this.tenBenhVien = tenBenhVien;
        this.chuyenKhoa = chuyenKhoa;
    }

    public String getTenBacSi() {
        return tenBacSi;
    }

    public void setTenBacSi(String tenBacSi) {
        this.tenBacSi = tenBacSi;
    }

    public String getTenBenhVien() {
        return tenBenhVien;
    }

    public void setTenBenhVien(String tenBenhVien) {
        this.tenBenhVien = tenBenhVien;
    }

    public String getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }
}
