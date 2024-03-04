package dev.memocode.question_server.domain.external.external.user.repository;

import dev.memocode.question_server.domain.external.external.user.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Author, UUID> {
}
