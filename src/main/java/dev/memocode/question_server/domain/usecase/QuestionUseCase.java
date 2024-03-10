package dev.memocode.question_server.domain.usecase;

import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;

import java.util.UUID;

public interface QuestionUseCase {

    UUID createQuestion(QuestionCreateDto questionCreateDto);
}
