package dev.memocode.question_server.domain.question.repository;

import dev.memocode.question_server.domain.question.entity.Question;

import java.util.Optional;
import java.util.UUID;

public interface QuestionRepositoryCustom {
    Optional<Question> findById(UUID id);
}
