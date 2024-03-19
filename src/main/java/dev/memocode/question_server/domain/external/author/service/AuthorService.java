package dev.memocode.question_server.domain.external.author.service;

import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.external.author.repository.AuthorRepository;
import dev.memocode.question_server.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static dev.memocode.question_server.exception.GlobalErrorCode.ACCOUNT_ID_CLAIM_NAME;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author findByAccountIdElseThrow(UUID userId) {
        return authorRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(ACCOUNT_ID_CLAIM_NAME));
    }
}
