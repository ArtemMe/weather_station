package com.artemme.resource_server.services;

import com.artemme.resource_server.dao.StationRepository;
import com.artemme.resource_server.dao.entity.Station;
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
