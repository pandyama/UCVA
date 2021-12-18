package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = TreatmentType.TreatmentTypeBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "TreatmentTypeBuilder", toBuilder = true)

public class TreatmentType {

    private int treatmentID;
    private String treatmentDescription;

    @JsonPOJOBuilder(withPrefix = "")
    public static class TreatmentTypeBuilder{}
}
