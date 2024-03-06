package dev.memocode.question_server.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TagCreateDto {

    private String name;
}
