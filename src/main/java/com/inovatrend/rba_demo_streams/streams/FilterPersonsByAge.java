package com.inovatrend.rba_demo_streams.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;

import com.inovatrend.rba_demo_streams.serde.PersonSerde;

import java.util.Properties;

public class FilterPersonsByAge {

    public static void main(String[] args) {

        Properties config = new Properties();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PersonSerde.class);
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "persons-by-age-app");
        config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);


    }
}
