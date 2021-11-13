package Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = User.UserBuilder.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(builderClassName = "UserBuilder", toBuilder = true)
public class User {

    private String username;
    private String role;
    private String designation;
    private String password;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserBuilder{}

}
