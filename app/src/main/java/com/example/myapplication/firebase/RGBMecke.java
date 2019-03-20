package com.example.myapplication.firebase;

public class RGBMecke {

    String rMecke;
    String gMecke;
    String bMecke;

    public RGBMecke() {
    }

    public RGBMecke(String rMecke, String gMecke, String bMecke) {
        this.rMecke = rMecke;
        this.gMecke = gMecke;
        this.bMecke = bMecke;
    }

    public String getrMarckis() {
        return rMecke;
    }

    public void setrMarckis(String rMarckis) {
        this.rMecke = rMarckis;
    }

    public String getgMecke() {
        return gMecke;
    }

    public void setgMecke(String gMecke) {
        this.gMecke = gMecke;
    }

    public String getbMecke() {
        return bMecke;
    }

    public void setbMecke(String bMecke) {
        this.bMecke = bMecke;
    }
}
