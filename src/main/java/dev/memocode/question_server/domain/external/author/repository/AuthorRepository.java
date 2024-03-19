package dev.memocode.question_server.domain.external.author.repository;

import dev.memocode.question_server.domain.external.author.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
