package com.artemme.resource_server.dao.impl;

import com.artemme.resource_server.dao.StationRepository;
import com.artemme.resource_server.dao.entity.Station;
import com.artemme.resource_server.services.HBService;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
public class StationRepositoryImpl implements StationRepository {

    static final byte[] INFO_COLUMNFAMILY = Bytes.toBytes("info");
    static final byte[] NAME_QUALIFIER = Bytes.toBytes("name");
    static final byte[] LOCATION_QUALIFIER = Bytes.toBytes("location");
    static final byte[] DESCRIPTION_QUALIFIER = Bytes.toBytes("description");
    static final TableName TABLE_NAME = TableName.valueOf("stations");


    @Autowired
    HBService hbService;

    @Override
    public List<Station> findAll() {
        return null;
    }

    @Override
    public Station find(String id) {
        try {
            Table stations = getTable();

            Get get = new Get(Bytes.toBytes(id));
            get.addFamily(INFO_COLUMNFAMILY);
            Result res = stations.get(get);

            if(res == null) return null;

            return new Station(
                    getValue(res, INFO_COLUMNFAMILY, NAME_QUALIFIER),
                    getValue(res, INFO_COLUMNFAMILY, LOCATION_QUALIFIER),
                    getValue(res, INFO_COLUMNFAMILY, DESCRIPTION_QUALIFIER)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void save(Station station) {
        Table stations = null;
        try {
            stations = getTable();
            Put put  = new Put(Bytes.toBytes(station.getId()));
            put.add(INFO_COLUMNFAMILY, NAME_QUALIFIER, station.getName().getBytes());
            put.add(INFO_COLUMNFAMILY, LOCATION_QUALIFIER, station.getName().getBytes());
            put.add(INFO_COLUMNFAMILY, DESCRIPTION_QUALIFIER, station.getName().getBytes());

            stations.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(stations !=null) {
                try {
                    stations.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getValue(Result res, byte[] cf, byte[] qualifier) {
        byte[] value = res.getValue(cf, qualifier);
        return value == null? "": Bytes.toString(value);
    }

    private Table getTable() throws IOException {
        Connection connection = hbService.createConnection();
        return connection.getTable(TABLE_NAME);
    }
}
