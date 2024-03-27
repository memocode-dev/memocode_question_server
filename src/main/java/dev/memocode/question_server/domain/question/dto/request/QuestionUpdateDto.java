package dev.memocode.question_server.domain.question.dto.request;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private UUID questionId;
    @NotNull
    private UUID userId;
    private String title;
    private String content;
}