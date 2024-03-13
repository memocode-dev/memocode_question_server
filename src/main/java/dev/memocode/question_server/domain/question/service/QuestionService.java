package dev.memocode.question_server.domain.question.service;

import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.external.author.service.AuthorService;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepository;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.repository.QuestionRepositoryCustom;
import dev.memocode.question_server.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

import static dev.memocode.question_server.exception.GlobalErrorCode.*;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionRepositoryCustom questionRepositoryCustom;
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
    public void deleteQuestion(QuestionDeleteDto questionDeleteDto) {
        Question question = findQuestionById(questionDeleteDto.getQuestionId());
        validateOwner(questionDeleteDto.getAccountId(), question);
        question.delete();
    }
    @Transactional
    public UUID updateQuestion(QuestionUpdateDto questionUpdateDto) {
        Question question = findQuestionById(questionUpdateDto.getQuestionId());
        validateOwner(questionUpdateDto.getAccountId(), question);
        question.update(questionUpdateDto.getTitle(), questionUpdateDto.getContent());
        return question.getId();
    }
    private void validateOwner(UUID accountId, Question question) {
        if (Objects.equals(accountId , question.getAuthor().getAccountId())) return;
        throw new GlobalException(NOT_VALID_QUESTION_OWNER);
    }

    private Question findQuestionById(UUID questionId) {
        return questionRepositoryCustom.findById(questionId)
                .orElseThrow(() -> new GlobalException(QUESTION_NOT_FOUND));
    }
}
