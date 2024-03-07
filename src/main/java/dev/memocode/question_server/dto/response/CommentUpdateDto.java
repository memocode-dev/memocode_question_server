package dev.memocode.question_server.dto.response;

import dev.memocode.question_server.dto.form.CommentUpdateForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateDto {

    private CommentUpdateForm commentUpdateForm;
    private Instant updatedAt;
}
