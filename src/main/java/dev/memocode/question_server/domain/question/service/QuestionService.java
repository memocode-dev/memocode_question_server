package dev.memocode.question_server.domain.question.service;

import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.external.author.service.AuthorService;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepository;
import dev.memocode.question_server.domain.question.repository.QuestionRepositoryCustom;
import dev.memocode.question_server.domain.tag.service.TagService;
import dev.memocode.question_server.exception.GlobalException;
import dev.memocode.question_server.usecase.QuestionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

import static dev.memocode.question_server.exception.GlobalErrorCode.NOT_VALID_QUESTION_OWNER;
import static dev.memocode.question_server.exception.GlobalErrorCode.QUESTION_NOT_FOUND;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class QuestionService implements QuestionUseCase {

    private final QuestionRepository questionRepository;
    private final QuestionRepositoryCustom questionRepositoryCustom;
    private final AuthorService authorService;
    private final TagService tagService;
    @Transactional
    public UUID createQuestion(QuestionCreateDto questionCreateDto) {

        Author author = authorService.findByAccountIdElseThrow(questionCreateDto.getUserId());
        Question question = Question.builder()
                .title(questionCreateDto.getTitle())
                .content(questionCreateDto.getContent())
                .author(author)
                .build();
        tagService.createTag(question, questionCreateDto.getTags());
        return questionRepository.save(question).getId();
    }
    @Transactional
    public void deleteQuestion(QuestionDeleteDto questionDeleteDto) {
        Question question = findQuestionById(questionDeleteDto.getQuestionId());
        validateOwner(questionDeleteDto.getUserId(), question);
        question.delete();
    }
    @Transactional
    public UUID updateQuestion(QuestionUpdateDto questionUpdateDto) {
        Question question = findQuestionById(questionUpdateDto.getQuestionId());
        validateOwner(questionUpdateDto.getUserId(), question);
        question.update(questionUpdateDto.getTitle(), questionUpdateDto.getContent());
        return question.getId();
    }

    public Page<QuestionDetailDto> findAllQuestion(Pageable pageable) {
        return questionRepositoryCustom.findAllQuestion(pageable);
    }

    private void validateOwner(UUID accountId, Question question) {
        if (Objects.equals(accountId , question.getAuthor().getId())) return;
        throw new GlobalException(NOT_VALID_QUESTION_OWNER);
    }

    public Question findQuestionById(UUID questionId) {
        return questionRepositoryCustom.findById(questionId)
                .orElseThrow(() -> new GlobalException(QUESTION_NOT_FOUND));
    }
}
