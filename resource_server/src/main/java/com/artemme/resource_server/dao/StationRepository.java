package com.artemme.resource_server.dao;

import com.artemme.resource_server.dao.entity.Station;

import java.util.List;

public interface StationRepository {
    List<Station> findAll();
    Station find(String id);
    void save(Station station);
}
