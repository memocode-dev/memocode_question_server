package dev.memocode.question_server.domain.usecase.impl;

import dev.memocode.question_server.domain.user.service.AuthorService;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.service.QuestionService;
import dev.memocode.question_server.domain.usecase.QuestionUseCase;
import dev.memocode.question_server.dto.request.QuestionCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Component
@Transactional
@RequiredArgsConstructor
public class QuestionUseCaseImpl implements QuestionUseCase {

    private final QuestionService questionService;
    private final AuthorService authorService;

    @Override
    public UUID createQuestion(QuestionCreateDto questionCreateDto) {
        Question question = questionService.createQuestion(questionCreateDto);
        return question.getId();
    }
}
