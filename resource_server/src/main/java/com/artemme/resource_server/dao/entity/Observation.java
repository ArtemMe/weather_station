package com.artemme.resource_server.dao.entity;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Observation {
    List<String> temperature;
    NavigableMap<Long, Integer> data = new TreeMap<>();

    public List<String> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<String> temperature) {
        this.temperature = temperature;
    }

    public NavigableMap<Long, Integer> getData() {
        return data;
    }

    public void setData(Long date, Integer temp) {
        data.put(date, temp);
    }
}
