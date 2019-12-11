package com.artemme.resource_server.controllers;

import com.artemme.resource_server.dao.entity.Observation;
import com.artemme.resource_server.dto.Message;
import com.artemme.resource_server.services.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSockerController {

    private String stationId = "036170-99999";

    @Autowired
    ObservationService observationService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Observation send(Message message) throws Exception {
        Observation observation = observationService.getData(stationId, 10); //todo пока не понятно что будет в сущнсти.
        return observation;
    }
}
