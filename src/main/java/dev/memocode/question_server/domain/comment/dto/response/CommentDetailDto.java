package dev.memocode.question_server.domain.comment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDetailDto {

    private String content;
    private UUID authorId;
    private Long questionId;
    private Long parentCommentId;
    private Instant createdAt;
    private Instant updatedAt;
}
