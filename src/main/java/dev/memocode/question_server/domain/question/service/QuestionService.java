package dev.memocode.question_server.domain.question.service;

import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.external.author.service.AuthorService;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.mapper.QuestionMapper;
import dev.memocode.question_server.domain.question.repository.QuestionRepository;
import dev.memocode.question_server.domain.question.repository.QuestionRepositoryCustom;
import dev.memocode.question_server.domain.tag.entity.Tag;
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
import java.util.Optional;
import java.util.Set;
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
    private final QuestionMapper questionMapper;

    @Transactional
    @Override
    public UUID createQuestion(QuestionCreateDto questionCreateDto) {

        Author author = authorService.findByAccountIdElseThrow(questionCreateDto.getUserId());
        Set<Tag> tags = tagService.findAllTagOrCreateTagByName(questionCreateDto.getTags());

        Question question = Question.builder()
                .title(questionCreateDto.getTitle())
                .content(questionCreateDto.getContent())
                .author(author)
                .build();

        question.addTags(tags);

        Question savedQuestion = questionRepository.save(question);

        return savedQuestion.getId();
    }

    @Transactional
    @Override
    public void deleteQuestion(QuestionDeleteDto questionDeleteDto) {
        validateQuestionOwner(questionDeleteDto.getUserId(), questionDeleteDto.getQuestionId());

        Question question = findByIdElseThrow(questionDeleteDto.getQuestionId());

        question.delete();
    }

    @Transactional
    @Override
    public UUID updateQuestion(QuestionUpdateDto questionUpdateDto) {
        validateQuestionOwner(questionUpdateDto.getUserId(), questionUpdateDto.getQuestionId());

        Question question = findByIdElseThrow(questionUpdateDto.getQuestionId());

        question.update(questionUpdateDto.getTitle(), questionUpdateDto.getContent());
        return question.getId();
    }

    @Override
    public Page<QuestionDetailDto> findAllQuestion(Pageable pageable) {
        Page<Question> questions = questionRepositoryCustom.findAllQuestion(pageable);

        return questionMapper.pageEntities_to_pageDtos(questions);
    }

    @Override
    public QuestionDetailDto findQuestion(UUID questionId) {
        Question question = findByIdElseThrow(questionId);

        return questionMapper.entity_to_dto(question);
    }

    private void validateQuestionOwner(UUID authorId, UUID questionId) {

        Question question = findByIdElseThrow(questionId);

        if (!Objects.equals(authorId , question.getAuthor().getId())) {
            throw new GlobalException(NOT_VALID_QUESTION_OWNER);
        }
    }

    private Optional<Question> findById(UUID questionId) {
        return questionRepository.findById(questionId);
    }

    private Question findByIdElseThrow(UUID questionId) {
        return findById(questionId)
                .orElseThrow(() -> new GlobalException(QUESTION_NOT_FOUND));
    }
}
