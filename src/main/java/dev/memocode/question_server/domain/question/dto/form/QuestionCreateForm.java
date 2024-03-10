package dev.memocode.question_server.domain.question.dto.form;

import dev.memocode.question_server.domain.tag.dto.request.TagCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateForm {
    @Schema(requiredMode = REQUIRED)
    private String title;

    @Schema(requiredMode = REQUIRED)
    private String content;

    Set<TagCreateDto> tags = new HashSet<>();
}
