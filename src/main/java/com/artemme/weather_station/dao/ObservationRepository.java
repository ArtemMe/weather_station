package com.artemme.weather_station.dao;

import com.artemme.weather_station.dao.entity.Observation;
import com.artemme.weather_station.dao.entity.Station;

import java.util.List;

public interface ObservationRepository {
    List<Station> findAll();
    Observation find(String stationId, long maxStamp, int maxCount);
}
