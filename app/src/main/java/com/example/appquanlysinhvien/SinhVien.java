package com.example.appquanlysinhvien;

import java.io.Serializable;

public class SinhVien implements Serializable {
    String masv,tensv,lopsv;
    float dToan,dTin,dTiengAnh;

    public SinhVien(){
    }

    public SinhVien(String masv, String tensv, String lopsv, float dToan, float dTin, float dTiengAnh) {
        this.masv = masv;
        this.tensv = tensv;
        this.lopsv = lopsv;
        this.dToan = dToan;
        this.dTin = dTin;
        this.dTiengAnh = dTiengAnh;
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public String getTensv() {
        return tensv;
    }

    public void setTensv(String tensv) {
        this.tensv = tensv;
    }

    public String getLopsv() {
        return lopsv;
    }

    public void setLopsv(String lopsv) {
        this.lopsv = lopsv;
    }

    public float getdToan() {
        return dToan;
    }

    public void setdToan(float dToan) {
        this.dToan = dToan;
    }

    public float getdTin() {
        return dTin;
    }

    public void setdTin(float dTin) {
        this.dTin = dTin;
    }

    public float getdTiengAnh() {
        return dTiengAnh;
    }

    public void setdTiengAnh(float dTiengAnh) {
        this.dTiengAnh = dTiengAnh;
    }

    public float getTrungBinh(){
        float tong= this.dToan + this.dTin+ this.dTiengAnh;
        return tong/3;
    }
}
