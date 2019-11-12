package com.inovatrend.rba_demo_streams.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.inovatrend.rba_demo_streams.model.Person;

import java.util.Map;

public class PersonSerde implements Serde<Person> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }

    @Override
    public Serializer<Person> serializer() {
        return new PersonSerializer();
    }

    @Override
    public Deserializer<Person> deserializer() {
        return new PersonDeserializer();
    }
}
