package dev.memocode.question_server.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class TagsDto {

    private Set<TagDetailDto> tagDetailDtos;
}
