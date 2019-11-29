package com.artemme.resource_server.dao;

import com.artemme.resource_server.dao.entity.Observation;
import com.artemme.resource_server.dao.entity.Station;

import java.util.List;

public interface ObservationRepository {
    List<Station> findAll();
    Observation find(String stationId, long maxStamp, int maxCount);
}
