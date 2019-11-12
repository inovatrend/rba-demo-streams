package com.inovatrend.rba_demo_streams.serde;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import com.inovatrend.rba_demo_streams.model.Person;

import java.io.IOException;
import java.util.Map;

public class PersonDeserializer implements Deserializer<Person> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Person deserialize(String topic, byte[] data) {
        try {
            return mapper.readValue(data, Person.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {

    }
}
