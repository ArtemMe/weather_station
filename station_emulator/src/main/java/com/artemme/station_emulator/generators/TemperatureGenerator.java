package com.artemme.station_emulator.generators;

import org.springframework.stereotype.Component;

/**
 * Генерирует температуру в зависимости от id города в формате ncdc
 *
 * */

@Component
public class TemperatureGenerator {
    public String generateData(String city_id) {
        return "0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9-00781+99999102001ADDGF108991999999999999999999";
    }
}
