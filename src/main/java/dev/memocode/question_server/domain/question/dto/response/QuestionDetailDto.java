package dev.memocode.question_server.domain.question.dto.response;

import dev.memocode.question_server.domain.external.author.dto.AuthorDto;
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

    private UUID questionId;
    private String title;
    private String content;
    private Integer affinity;
    private Instant createdAt;
    private TagsDto tags;
    private AuthorDto author;
}
