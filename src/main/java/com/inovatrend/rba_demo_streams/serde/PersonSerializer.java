package com.inovatrend.rba_demo_streams.serde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import com.inovatrend.rba_demo_streams.model.Person;

import java.util.Map;

public class PersonSerializer implements Serializer<Person> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void configure(Map configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Person data) {
        if (data != null) {
            try {
                return mapper.writeValueAsBytes(data);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        }
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
