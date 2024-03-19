package dev.memocode.question_server.domain.question.repository;

import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface QuestionRepositoryCustom {
    Optional<Question> findById(UUID id);

    Page<QuestionDetailDto> findAllQuestion(Pageable pageable);
}
