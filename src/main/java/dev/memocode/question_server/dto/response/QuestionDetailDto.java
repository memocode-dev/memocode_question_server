package dev.memocode.question_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDetailDto {

    private String title;
    private String content;
    private Integer affinity;
    private Instant createdAt;
    private Instant updatedAt;
}
