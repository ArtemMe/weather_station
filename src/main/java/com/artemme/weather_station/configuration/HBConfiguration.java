package com.artemme.weather_station.configuration;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class HBConfiguration {

    @Bean
    public Configuration hbaseConfig() {
        Configuration config = HBaseConfiguration.create();
        String path = this.getClass().getClassLoader().getResource("hbase-site.xml").getPath();
        config.addResource(new Path(path));
        return HBaseConfiguration.create(config);
    }
}
