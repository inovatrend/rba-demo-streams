package com.inovatrend.rba_demo_streams.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import com.inovatrend.rba_demo_streams.model.Person;
import com.inovatrend.rba_demo_streams.serde.PersonSerde;

import java.util.Properties;

public class CityPopulationCountingApp {


    public static void main(String[] args) {

        Properties config = new Properties();
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class);
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PersonSerde.class);
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "city-population-app");
        config.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
        config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);

        StreamsBuilder builder = new StreamsBuilder();

        KTable<String, Person> personTable = builder.table("person-topic");

        personTable
                .groupBy((key, value) -> new KeyValue<>(value.getCity(), value))
                .count()
                .mapValues(value -> value.toString())
                .toStream()
                .to("city-population", Produced.with(new Serdes.StringSerde(), new Serdes.StringSerde()));

        KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
    }

}
