package dev.memocode.question_server.domain.question.dto.request;

import dev.memocode.question_server.domain.tag.dto.request.TagCreateDto;
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
    private String title;
    private String content;
    private UUID userId;
    Set<TagCreateDto> tags = new HashSet<>();
}
