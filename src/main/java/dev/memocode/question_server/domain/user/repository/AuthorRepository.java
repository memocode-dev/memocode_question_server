package dev.memocode.question_server.domain.user.repository;

import dev.memocode.question_server.domain.user.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByAccountId(UUID accountId);
}
