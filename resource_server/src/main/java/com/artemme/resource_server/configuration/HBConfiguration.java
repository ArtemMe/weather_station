package com.artemme.resource_server.configuration;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
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
