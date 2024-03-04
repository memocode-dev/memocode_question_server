package dev.memocode.question_server.dto;

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
public class MemoCreateForm {
    @Schema(requiredMode = REQUIRED)
    private String title;

    @Schema(requiredMode = REQUIRED)
    private String content;
}
