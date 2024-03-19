package dev.memocode.question_server.domain.question.mapper;

import dev.memocode.question_server.domain.question.dto.form.QuestionCreateForm;
import dev.memocode.question_server.domain.question.dto.form.QuestionUpdateForm;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuestionDtoMapper {

    public QuestionCreateDto fromQuestionCreateFormAndUserId(QuestionCreateForm form, UUID userId) {
        return QuestionCreateDto.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .userId(userId)
                .tags(form.getTags())
                .build();
    }

    public QuestionDeleteDto fromQuestionDeleteFormAndAccountId(UUID questionId, UUID accountId) {
        return QuestionDeleteDto.builder()
                .questionId(questionId)
                .userId(accountId)
                .build();
    }

    public QuestionUpdateDto fromQuestionUpdateFormAndAccountId(UUID questionId, QuestionUpdateForm form, UUID accountId) {
        return QuestionUpdateDto.builder()
                .questionId(questionId)
                .userId(accountId)
                .title(form.getTitle())
                .content(form.getContent())
                .build();
    }
}
