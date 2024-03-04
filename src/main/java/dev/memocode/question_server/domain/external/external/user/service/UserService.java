package dev.memocode.question_server.domain.external.external.user.service;

import dev.memocode.question_server.domain.external.external.user.entity.Author;
import dev.memocode.question_server.domain.external.external.user.repository.UserRepository;
import dev.memocode.question_server.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Author findByIdElseThrow(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new GlobalException(null));
    }
}
