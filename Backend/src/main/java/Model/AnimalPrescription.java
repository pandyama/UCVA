package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = AnimalPrescription.AnimalPrescriptionBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AnimalPrescriptionBuilder", toBuilder = true)

public class AnimalPrescription {

    private int prescriptionID;
    private String submissionDate;
    private int diagnosisID;
    private String prescriptionDetails;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalPrescriptionBuilder{}
}
