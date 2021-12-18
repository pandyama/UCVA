package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = AnimalTreatment.AnimalTreatmentBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AnimalHealthBuilder", toBuilder = true)


public class AnimalTreatment {

    private int treatmentID;
    private int animalID;
    private String treatmentStatus;
    private String treatmentDate;
    private String submissionDate;
    private int treatmentTypeID; // this links to Model.TreatmentType.treatmentID
    private int careAttendantID;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalTreatmentBuilder{}
}
