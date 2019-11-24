package com.artemme.weather_station.controllers;

import com.artemme.weather_station.dao.entity.Station;
import com.artemme.weather_station.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {

    @Autowired
    StationService stationService;

    @RequestMapping("/stationInfo/{id}")
    public Station getStationInfo(@PathVariable String id) {
        return stationService.getStationInfo(id);
    }
}
