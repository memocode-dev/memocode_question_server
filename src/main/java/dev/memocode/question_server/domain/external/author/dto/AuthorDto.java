package dev.memocode.question_server.domain.external.author.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDto {

    private String username;
    private String nickname;
    private UUID authorId;
}
