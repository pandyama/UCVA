package Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = Animal.AnimalBuilder.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "AnimalBuilder", toBuilder = true)
public class Animal {

    private int tattooNumber;
    private String cityTattoo;
    private String name;
    private int age;
    private double weight;
    private String sex;
    private String bDay;
    private String bMonth;
    private String bYear;
    private String breed;
    private String coatColor;
    private String problem;
    private String comment;
    private String[] continuousMedication;
    private String specialInstructions;
    private String specialDiet;
    private int active;
    private int rfid;
    private int microchip;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalBuilder{}

}
