package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = DiseaseAlert.DiseaseAlertBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "DiseaseAlertBuilder", toBuilder = true)
public class DiseaseAlert {

    private int alertID;
    private int careAttendantID;
    private String reportedDate;
    private String alertDetails;
    private String locationAffected;
    private String outbreakStatus;
    private String submissionDate;

    @JsonPOJOBuilder(withPrefix = "")
    public static class DiseaseAlertBuilder{}
}
