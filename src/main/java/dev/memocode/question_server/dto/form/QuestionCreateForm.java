package dev.memocode.question_server.dto.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // List<tagDto> 추가하기
}
