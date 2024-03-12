package dev.memocode.question_server.domain.question.service;

import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.external.author.service.AuthorService;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepository;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.exception.GlobalErrorCode;
import dev.memocode.question_server.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static dev.memocode.question_server.exception.GlobalErrorCode.*;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AuthorService authorService;
    @Transactional
    public Question createQuestion(QuestionCreateDto questionCreateDto) {

        Author author = authorService.findByAccountIdElseThrow(questionCreateDto.getAccountId());
        Question question = Question.builder()
                .title(questionCreateDto.getTitle())
                .content(questionCreateDto.getContent())
                .author(author)
                .build();
        return questionRepository.save(question);
    }
    @Transactional
    public void deleteQuestion(QuestionDeleteDto dto) {
        questionRepository.findById(dto.getQuestionId())
                .ifPresentOrElse(question -> {
                    validateOwner(dto, question);
                    question.delete();
                }, () -> {
                    throw new GlobalException(QUESTION_NOT_FOUND);
                });
    }

    private void validateOwner(QuestionDeleteDto dto, Question question) {
        if (Objects.equals(question.getAuthor().getAccountId(), dto.getAccountId())) return;
        throw new GlobalException(NOT_VALID_QUESTION_OWNER);
    }
}
