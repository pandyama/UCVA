package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = AnimalComments.AnimalCommentsBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AnimalCommentsBuilder", toBuilder = true)

public class AnimalComments {

    private int commentID;
    private int uID;
    private int animalID;
    private String comment;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalCommentsBuilder{}
}
