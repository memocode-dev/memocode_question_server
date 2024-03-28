package dev.memocode.question_server.domain.tag.repository;

import dev.memocode.question_server.domain.tag.entity.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, UUID> {
}
