package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = Animal.AnimalBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AnimalBuilder", toBuilder = true)
public class Animal {

    private int animalID;
    private int borrowID;
    private int tattooNumber;
    private String cityTattoo;
    private String name;
    private int age;
    private int weight;
    private String sex;
    private String birthDate;
    private String breed;
    private String coatColor;
    private String species;
    private String purpose;
    private int borrowStatus;// 0 is available, 1 is borrowed
    private String permanentLocation;
    private String profileImage;
    private int active;
    private int rfid;
    private int microchip;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalBuilder{}
}
