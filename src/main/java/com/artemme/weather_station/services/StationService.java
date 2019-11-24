package com.artemme.weather_station.services;

import com.artemme.weather_station.dao.StationRepository;
import com.artemme.weather_station.dao.entity.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {

    @Autowired
    StationRepository stationRepository;

    public Station getStationInfo(String id) {
        return stationRepository.find(id);
    }
}
