package dev.memocode.question_server.domain.usecase;

import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface QuestionUseCase {

    UUID createQuestion(QuestionCreateDto questionCreateDto);

    void deleteQuestion(QuestionDeleteDto questionDeleteDto);

    UUID updateQuestion(QuestionUpdateDto questionUpdateDto);

    void findQuestionById(UUID questionId);

     Page<QuestionDetailDto> findAllQuestion(Pageable pageable);
}
