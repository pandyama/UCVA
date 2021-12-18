package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = AccessFlags.AccessFlagsBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AccessFlagsBuilder", toBuilder = true)
public class AccessFlags {

    private int[] accessLevels;
    private String[] allowedOperations;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccessFlagsBuilder{}
}
