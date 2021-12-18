package com.project.Model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@JsonDeserialize(builder = Comment.CommentBuilder.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(builderClassName = "CommentBuilder", toBuilder = true)
public class Comment {

    private String user;
    private String text;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CommentBuilder{}
}
