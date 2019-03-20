package com.example.myapplication.firebase;

public class RGBSimon {

    String rSimon;
    String gSimon;
    String bSimon;

    public RGBSimon() {
    }

    public RGBSimon(String rMarckis, String gMarckis, String bSimon) {
        this.rSimon = rMarckis;
        this.gSimon = gMarckis;
        this.bSimon = bSimon;
    }

    public String getrSimon() {
        return rSimon;
    }

    public void setrSimon(String rSimon) {
        this.rSimon = rSimon;
    }

    public String getgSimon() {
        return gSimon;
    }

    public void setgSimon(String gSimon) {
        this.gSimon = gSimon;
    }

    public String getbSimon() {
        return bSimon;
    }

    public void setbSimon(String bSimon) {
        this.bSimon = bSimon;
    }
}
