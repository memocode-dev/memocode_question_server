package dev.memocode.question_server.mapper;

import dev.memocode.question_server.dto.form.QuestionCreateForm;
import dev.memocode.question_server.dto.request.QuestionCreateDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuestionCreateDtoMapper {

    public QuestionCreateDto fromQuestionCreateFormAndAccountId(QuestionCreateForm form, UUID accountId) {
        return QuestionCreateDto.builder()
                .title(form.getTitle())
                .content(form.getContent())
                .accountId(accountId)
                .build();
    }
}
