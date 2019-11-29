package com.artemme.resource_server.controllers;

import com.artemme.resource_server.dao.entity.Station;
import com.artemme.resource_server.services.StationService;
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
