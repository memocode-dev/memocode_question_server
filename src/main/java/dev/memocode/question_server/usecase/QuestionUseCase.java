package dev.memocode.question_server.usecase;

import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface QuestionUseCase {

    UUID createQuestion(@Valid QuestionCreateDto questionCreateDto);

    void deleteQuestion(QuestionDeleteDto questionDeleteDto);

    UUID updateQuestion(@Valid QuestionUpdateDto questionUpdateDto);

    QuestionDetailDto findQuestion(UUID questionId);

     Page<QuestionDetailDto> findAllQuestion(Pageable pageable);
}
