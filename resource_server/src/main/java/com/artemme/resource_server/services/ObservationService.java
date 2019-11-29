package com.artemme.resource_server.services;

import com.artemme.resource_server.dao.ObservationRepository;
import com.artemme.resource_server.dao.entity.Observation;
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
