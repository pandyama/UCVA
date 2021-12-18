package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = Student.StudentBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "UserBuilder", toBuilder = true)
public class Student {

    private int studentID;

    @JsonPOJOBuilder(withPrefix = "")
    public static class StudentBuilder{}
}
