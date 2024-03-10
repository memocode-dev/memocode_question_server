package dev.memocode.question_server.domain.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionUpdateDto {
    private Long questionId;
    private UUID accountId;
    private String title;
    private String content;
}