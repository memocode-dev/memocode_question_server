package dev.memocode.question_server.domain.user.service;

import dev.memocode.question_server.domain.user.entity.Author;
import dev.memocode.question_server.domain.user.repository.AuthorRepository;
import dev.memocode.question_server.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author findByIdElseThrow(UUID userId) {
        return authorRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(null));
    }
}
