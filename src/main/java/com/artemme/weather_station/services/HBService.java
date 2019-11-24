package com.artemme.weather_station.services;

import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HBService {

    @Autowired
    Configuration hbaseConfig;

    public Connection createConnection() throws IOException {
        return ConnectionFactory.createConnection(hbaseConfig);
    }

    public Admin getAdmin() throws IOException {
        return createConnection().getAdmin();
    }
}
