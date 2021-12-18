package com.project.Model;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = AnimalHealth.AnimalHealthBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AnimalHealthBuilder", toBuilder = true)
public class AnimalHealth {

    private int healthID;
    private int animalID;
    private int healthStatus;
    private String continuousMedication;
    private String specialInstructions;
    private String specialDiet;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalHealthBuilder{}
}
