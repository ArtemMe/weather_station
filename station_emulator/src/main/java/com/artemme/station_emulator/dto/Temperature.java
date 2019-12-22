package com.artemme.station_emulator.dto;

public class Temperature {

   private String data;

    public Temperature(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
