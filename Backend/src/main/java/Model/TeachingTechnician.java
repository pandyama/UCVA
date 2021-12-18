package com.project.Model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = TeachingTechnician.TeachingTechnicianBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "TeachingTechnicianBuilder", toBuilder = true)
public class TeachingTechnician {

    private int TeachingTechnicianID;

    @JsonPOJOBuilder(withPrefix = "")
    public static class NonAdminBuilder{}
}
