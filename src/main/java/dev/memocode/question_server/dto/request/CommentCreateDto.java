package dev.memocode.question_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDto {

    private String title;
    private String content;
    private UUID accountId;
    private Long questionId;
    private Long parentCommentId;
}
