package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = Admin.AdminBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AdminBuilder", toBuilder = true)
public class Admin {

    private int adminID;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AdminBuilder{}
}
