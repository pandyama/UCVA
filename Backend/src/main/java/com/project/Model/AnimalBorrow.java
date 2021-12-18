package com.project.Model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = AnimalBorrow.AnimalBorrowBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "AnimalHealthBuilder", toBuilder = true)

public class AnimalBorrow {

    private int animalID;
    private Date borrowDate;
    private Date returnDate;
    private int borrowerID;
    private String purpose;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AnimalBorrowBuilder{}
}
