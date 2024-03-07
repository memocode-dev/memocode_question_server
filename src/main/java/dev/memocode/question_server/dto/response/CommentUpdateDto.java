package dev.memocode.question_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateDto {

    private String content;
    private UUID authorId;
    private Long questionId;
    private Long parentCommentId;
}