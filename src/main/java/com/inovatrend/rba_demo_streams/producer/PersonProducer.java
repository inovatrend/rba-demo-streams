package com.inovatrend.rba_demo_streams.producer;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.inovatrend.rba_demo_streams.model.Person;
import com.inovatrend.rba_demo_streams.serde.PersonSerializer;

import java.util.Properties;
import java.util.Scanner;

public class PersonProducer {

    public static void main(String[] args) {

        Properties config = new Properties();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PersonSerializer.class);

        KafkaProducer<String, Person> producer = new KafkaProducer<>(config);

        String topic = "person-topic";

        boolean more = true;

        while (more) {

            Scanner scanner = new Scanner(System.in);

            String name = readString(scanner, "name");
            String city = readString(scanner, "city");
            String favColor = readString(scanner, "favourite color");
            int age = readInt(scanner, "age");

            Person person = new Person(name, city, age, favColor);
            producer.send(new ProducerRecord<>(topic, name, person));
            System.out.println("Person " + name + " stored to " + topic);
            System.out.println("\nAdd another person? Y/n");
            String input = scanner.nextLine();
            more = StringUtils.isEmpty(input) || input.trim().equalsIgnoreCase("y");
        }

    }

    private static String readString(Scanner scanner, String fieldName) {
        System.out.println("Enter " + fieldName + ": ");
        String input = scanner.nextLine();
        while (StringUtils.isEmpty(input)) {
            System.out.println("Invalid " + fieldName + "! Try again: ");
            input = scanner.nextLine();
        }
        return input;
    }

    private static int readInt(Scanner scanner, String fieldName) {
        System.out.println("Enter " + fieldName + ": ");
        String input = scanner.nextLine();
        while (!StringUtils.isNumeric(input)) {
            System.out.println("Invalid " + fieldName + "! Try again: ");
            input = scanner.nextLine();
        }
        return Integer.parseInt(input);
    }

}
