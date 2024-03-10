package dev.memocode.question_server.domain.comment.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommentsDto {

    private List<CommentDetailDto> commentDetailDtos;
}
