package dev.memocode.question_server.domain.question.repository;

import dev.memocode.question_server.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
