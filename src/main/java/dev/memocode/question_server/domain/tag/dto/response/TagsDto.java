package dev.memocode.question_server.domain.tag.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@Builder
public class TagsDto {

    private List<String> tags;
}
