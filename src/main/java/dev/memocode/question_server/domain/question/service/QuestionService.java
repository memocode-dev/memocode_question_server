package dev.memocode.question_server.domain.question.service;

import dev.memocode.question_server.domain.external.external.user.repository.AuthorRepository;
import dev.memocode.question_server.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AuthorRepository authorRepository;


}
