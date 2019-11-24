package com.artemme.weather_station.dao.impl;

import com.artemme.weather_station.dao.ObservationRepository;
import com.artemme.weather_station.dao.entity.Observation;
import com.artemme.weather_station.dao.entity.Station;
import com.artemme.weather_station.services.HBService;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

@Repository
public class ObservationRepositoryImpl implements ObservationRepository {
    private static final byte[] DATA_COLUMNFAMILY = Bytes.toBytes("data");
    private static final byte[] AIRTEMP_QUALIFIER = Bytes.toBytes("airtemp");
    private static final TableName TABLE_NAME = TableName.valueOf("observations_2");
    private static final int STATION_ID_LENGTH = 12;


    @Autowired
    HBService hbService;

    @Override
    public List<Station> findAll() {
        return null;
    }

    @Override
    public Observation find(String stationId, long maxStamp, int maxCount) {
        byte[] startRow = makeObservationRowKey(stationId, maxStamp);
        Observation observation = new Observation();

        Scan scan = new Scan(startRow);
        scan.addColumn(DATA_COLUMNFAMILY, AIRTEMP_QUALIFIER);
        ResultScanner scanner = null;
        try {
            scanner = getTable().getScanner(scan);
            Result res;
            int count = 0;

            while ((res = scanner.next()) != null && count++ < maxCount) {
                byte[] row = res.getRow();
                byte[] value = res.getValue(DATA_COLUMNFAMILY, AIRTEMP_QUALIFIER);
                Long stamp = Long.MAX_VALUE -
                        Bytes.toLong(row, row.length - Bytes.SIZEOF_LONG, Bytes.SIZEOF_LONG);
                Integer temp = Bytes.toInt(value);
                observation.setData(stamp, temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(scanner != null) {
                scanner.close();
            }
        }

        return observation;
    }

    public static byte[] makeObservationRowKey(String stationId, long observationTime) {
        byte[] row = new byte[STATION_ID_LENGTH + Bytes.SIZEOF_LONG];
        Bytes.putBytes(row, 0, Bytes.toBytes(stationId), 0, STATION_ID_LENGTH);
        long reverseOrderTimestamp = Long.MAX_VALUE - observationTime;
        Bytes.putLong(row, STATION_ID_LENGTH, reverseOrderTimestamp);
        return row;
    }

    private Table getTable() throws IOException {
        Connection connection = hbService.createConnection();
        return connection.getTable(TABLE_NAME);
    }
}
