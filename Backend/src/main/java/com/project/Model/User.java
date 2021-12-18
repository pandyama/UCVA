package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = User.UserBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "UserBuilder", toBuilder = true)
public class User {

    private int userID;
    private String username;
    private String email;
    private String password;
    private String role; //admin, non-admin, student, technician etc..
    private int accessLevel;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserBuilder{}

}
