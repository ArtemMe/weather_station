package com.artemme.station_emulator.controllers;

import com.artemme.station_emulator.dto.Temperature;
import com.artemme.station_emulator.generators.TemperatureGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class AirTemperatureController {

    @Autowired
    private TemperatureGenerator temperatureGenerator;

    @RequestMapping("/v1/temperature/{cityId}")
    public Temperature getCurrentTemperature(@PathVariable String cityId) {
        return new Temperature(temperatureGenerator.generateData(cityId));
    }
}
