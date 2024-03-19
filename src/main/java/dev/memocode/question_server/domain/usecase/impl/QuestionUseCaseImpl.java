package dev.memocode.question_server.domain.usecase.impl;

import dev.memocode.question_server.domain.external.author.service.AuthorService;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.service.QuestionService;
import dev.memocode.question_server.domain.tag.entity.QuestionTag;
import dev.memocode.question_server.domain.tag.entity.Tag;
import dev.memocode.question_server.domain.tag.repository.QuestionTagRepository;
import dev.memocode.question_server.domain.tag.service.TagService;
import dev.memocode.question_server.domain.usecase.QuestionUseCase;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class QuestionUseCaseImpl implements QuestionUseCase {

    private final QuestionService questionService;
    private final AuthorService authorService;
    private final TagService tagService;

    @Override
    public UUID createQuestion(QuestionCreateDto questionCreateDto) {
        Question question = questionService.createQuestion(questionCreateDto);
        tagService.createTag(question, questionCreateDto.getTags());

        return question.getId();
    }

    @Override
    public void deleteQuestion(QuestionDeleteDto questionDeleteDto) {
        questionService.deleteQuestion(questionDeleteDto);
    }

    @Override
    public UUID updateQuestion(QuestionUpdateDto questionUpdateDto) {
        return questionService.updateQuestion(questionUpdateDto);
    }

    @Override
    public void findQuestionById(UUID questionId) {

    }

    @Override
    public Page<QuestionDetailDto> findAllQuestion(Pageable pageable) {
        return questionService.findAllQuestion(pageable);
    }
}
