package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = PrescriptionItem.PrescriptionItemBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "PrescriptionItemBuilder", toBuilder = true)

public class PrescriptionItem {
    private int id;
    private int prescriptionID;
    private String prescriptionType;
    private String prescriptionDetail;

    @JsonPOJOBuilder(withPrefix = "")
    public static class PrescriptionItemBuilder{}
}
