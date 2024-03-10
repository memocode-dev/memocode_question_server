package dev.memocode.question_server.domain.question.service;

import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.external.author.service.AuthorService;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepository;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AuthorService authorService;

    public Question createQuestion(QuestionCreateDto questionCreateDto) {

        log.info("USER = {}", questionCreateDto.getAccountId());

        Author author = authorService.findByAccountIdElseThrow(questionCreateDto.getAccountId());
        Question question = Question.builder()
                .title(questionCreateDto.getTitle())
                .content(questionCreateDto.getContent())
                .author(author)
                .build();
        return questionRepository.save(question);
    }
}
