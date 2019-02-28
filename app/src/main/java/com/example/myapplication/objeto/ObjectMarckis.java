package com.example.myapplication.objeto;

import android.os.Parcel;
import android.os.Parcelable;

    public class ObjectMarckis implements Parcelable {

        private String rMarckis;
        private String gMarckis;
        private String bMarckis;

        public ObjectMarckis(String rMarckis, String gMarckis, String bMarckis) {
            this.rMarckis = rMarckis;
            this.gMarckis = gMarckis;
            this.bMarckis = bMarckis;
        }

        public String getrMarckis() {
            return rMarckis;
        }

        public void setrMarckis(String rMarckis) {
            this.rMarckis = rMarckis;
        }

        public String getgMarckis() {
            return gMarckis;
        }

        public void setgMarckis(String gMarckis) {
            this.gMarckis = gMarckis;
        }

        public String getbMarckis() {
            return bMarckis;
        }

        public void setbMarckis(String bMarckis) {
            this.bMarckis = bMarckis;
        }

        protected ObjectMarckis(Parcel in) {
            rMarckis = in.readString();
            gMarckis = in.readString();
            bMarckis = in.readString();
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(rMarckis);
            dest.writeString(gMarckis);
            dest.writeString(bMarckis);
        }

        @SuppressWarnings("unused")
        public static final Parcelable.Creator<ObjectMarckis> CREATOR = new Parcelable.Creator<ObjectMarckis>() {
            @Override
            public ObjectMarckis createFromParcel(Parcel in) {
                return new ObjectMarckis(in);
            }

            @Override
            public ObjectMarckis[] newArray(int size) {
                return new ObjectMarckis[size];
            }
        };
    }