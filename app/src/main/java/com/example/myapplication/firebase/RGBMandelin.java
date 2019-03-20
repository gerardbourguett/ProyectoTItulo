package com.example.myapplication.firebase;

public class RGBMandelin {

    String rMandelin;
    String gMandelin;
    String bMandelin;

    public RGBMandelin() {
    }

    public RGBMandelin(String rMandelin, String gMandelin, String bMandelin) {
        this.rMandelin = rMandelin;
        this.gMandelin = gMandelin;
        this.bMandelin = bMandelin;
    }

    public String getrMandelin() {
        return rMandelin;
    }

    public void setrMandelin(String rMandelin) {
        this.rMandelin = rMandelin;
    }

    public String getgMandelin() {
        return gMandelin;
    }

    public void setgMandelin(String gMandelin) {
        this.gMandelin = gMandelin;
    }

    public String getbMandelin() {
        return bMandelin;
    }

    public void setbMandelin(String bMandelin) {
        this.bMandelin = bMandelin;
    }
}
