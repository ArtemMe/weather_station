package com.artemme.weather_station.dao;

import com.artemme.weather_station.dao.entity.Station;

import java.util.List;

public interface StationRepository {
    List<Station> findAll();
    Station find(String id);
}
