package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = HealthTechnician.HealthTechnicianBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "HealthTechnicianBuilder", toBuilder = true)
public class HealthTechnician {

    private int technicianID;

    @JsonPOJOBuilder(withPrefix = "")
    public static class HealthTechnicianBuilder{}
}
