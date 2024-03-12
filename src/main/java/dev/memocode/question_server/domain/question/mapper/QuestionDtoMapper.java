package dev.memocode.question_server.domain.question.mapper;

import dev.memocode.question_server.domain.question.dto.form.QuestionCreateForm;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuestionDtoMapper {

    public QuestionCreateDto fromQuestionCreateFormAndAccountId(QuestionCreateForm form, UUID accountId) {
        return QuestionCreateDto.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .accountId(accountId)
                .build();
    }

    public QuestionDeleteDto fromQuestionDeleteFormAndAccountId(UUID questionId, UUID accountId) {
        return QuestionDeleteDto.builder()
                .questionId(questionId)
                .accountId(accountId.toString())
                .build();
    }
}
