package dev.memocode.question_server.dto.response;

import dev.memocode.question_server.dto.form.QuestionUpdateForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionUpdateDto {
    private QuestionUpdateForm QuestionUpdateForm;
    private Instant updatedAt;
}
