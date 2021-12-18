package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = AnimalImages.AnimalImagesBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AnimalImagesBuilder", toBuilder = true)

public class AnimalImages {

    private int imageID;
    private int animalID;
    private String injuryImage;
    private String injuryType;
    private String injuryDate;
    private int uID;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalImagesBuilder{}
}
