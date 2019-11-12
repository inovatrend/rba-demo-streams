package com.inovatrend.rba_demo_streams.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    private String name;

    private String city;

    private int age;

    private String favColor;

    @JsonCreator
    public Person(@JsonProperty("name") String name,
                  @JsonProperty("city") String city,
                  @JsonProperty("age") int age,
                  @JsonProperty("favColor") String favColor) {

        this.name = name;
        this.city = city;
        this.age = age;
        this.favColor = favColor;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public String getFavColor() {
        return favColor;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                ", favColor='" + favColor + '\'' +
                '}';
    }
}
