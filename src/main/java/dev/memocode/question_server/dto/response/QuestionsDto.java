package dev.memocode.question_server.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionsDto {

    private int totalPage;
    private int currentPage;
    private boolean isLast;
    private List<QuestionDetailDto> questionDetailDtos;
}
