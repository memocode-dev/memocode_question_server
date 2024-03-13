package dev.memocode.question_server.domain.usecase;

import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;

import java.util.UUID;

public interface QuestionUseCase {

    UUID createQuestion(QuestionCreateDto questionCreateDto);

    void deleteQuestion(QuestionDeleteDto questionDeleteDto);

    UUID updateQuestion(QuestionUpdateDto questionUpdateDto);
}
