package dev.memocode.question_server.domain.question.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDto {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private UUID userId;
    @Builder.Default
    Set<String> tags = new HashSet<>();
}
