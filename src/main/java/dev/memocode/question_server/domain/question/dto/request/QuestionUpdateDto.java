package dev.memocode.question_server.domain.question.dto.request;

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
    private UUID questionId;
    private UUID userId;
    private String title;
    private String content;
}