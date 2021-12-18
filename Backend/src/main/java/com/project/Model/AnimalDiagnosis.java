package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = AnimalDiagnosis.AnimalDiagnosisBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AnimalHealthBuilder", toBuilder = true)

public class AnimalDiagnosis {

    private int diagnoseID;
    private int prescriptionID;
    private int animalID;
    private int healthTechnicianID;
    private String diagnosisDetail;
    private String submissionDate;
    private int uID;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalDiagnosisBuilder{}
}
