package dev.memocode.question_server.dto.response;

import dev.memocode.question_server.dto.request.TagCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

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
    private TagsDto tags;
}
