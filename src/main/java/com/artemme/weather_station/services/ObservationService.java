package com.artemme.weather_station.services;

import com.artemme.weather_station.dao.ObservationRepository;
import com.artemme.weather_station.dao.entity.Observation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservationService {

    @Autowired
    ObservationRepository observationRepository;

    public Observation getData(String stationId, int maxCount) {
        return observationRepository.find(stationId, Long.MAX_VALUE, maxCount);
    }
}
