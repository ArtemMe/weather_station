package com.artemme.resource_server.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TemperatureConsumer {
    private static final Logger log = LoggerFactory.getLogger(TemperatureConsumer.class);
    @Autowired
    private RestTemplate restTemplate;
    private final String serverUrl = "http://localhost:8083/data/v1/temperature/1";

    @Scheduled(fixedRate = 5000)
    public void getDataFromServer() {
        log.info(restTemplate.getForObject(serverUrl, String.class));
    }
}
