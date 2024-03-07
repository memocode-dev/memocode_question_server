package dev.memocode.question_server.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommentsDto {

    private List<CommentDetailDto> commentDetailDtos;
}
