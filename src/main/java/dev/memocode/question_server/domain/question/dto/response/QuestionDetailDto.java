package dev.memocode.question_server.domain.question.dto.response;

import dev.memocode.question_server.domain.tag.dto.response.TagsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDetailDto {

    private String title;
    private String content;
    private UUID authorId;
    private Integer affinity;
    private Instant createdAt;
    private Instant updatedAt;
    private TagsDto tags;
}
