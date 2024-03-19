package dev.memocode.question_server.domain.tag.dto.request;

import dev.memocode.question_server.domain.tag.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagCreateDto {

    private String name;
}
