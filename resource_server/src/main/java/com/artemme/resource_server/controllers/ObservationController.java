package com.artemme.resource_server.controllers;

import com.artemme.resource_server.dao.entity.Observation;
import com.artemme.resource_server.services.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObservationController {

    @Autowired
    ObservationService observationService;

    @RequestMapping("/observation/{id}")
    public Observation getData(@PathVariable String id,
                               @RequestParam Integer maxCount) {
        return observationService.getData(id, maxCount);
    }
}
