package dev.memocode.question_server.usecase;

import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface QuestionUseCase {

    UUID createQuestion(QuestionCreateDto questionCreateDto);

    void deleteQuestion(QuestionDeleteDto questionDeleteDto);

    UUID updateQuestion(QuestionUpdateDto questionUpdateDto);

    Question findQuestionById(UUID questionId);

     Page<QuestionDetailDto> findAllQuestion(Pageable pageable);
}
