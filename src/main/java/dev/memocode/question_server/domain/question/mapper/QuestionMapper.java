package dev.memocode.question_server.domain.question.mapper;

import dev.memocode.question_server.domain.external.author.dto.AuthorDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionMapper {

    public QuestionDetailDto entity_to_dto(Question question) {

        List<String> tags = question.getQuestionTags().stream()
                .map(questionTag -> questionTag.getTag().getName())
                .toList();

        AuthorDto author = AuthorDto.builder()
                .id(question.getAuthor().getId())
                .username(question.getAuthor().getUsername())
                .nickname(question.getAuthor().getNickname())
                .build();

        return QuestionDetailDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .tags(tags)
                .author(author)
                .build();
    }

    public List<QuestionDetailDto> entities_to_dtos(List<Question> questions) {
        return questions.stream()
                .map(this::entity_to_dto)
                .toList();
    }

    public Page<QuestionDetailDto> pageEntities_to_pageDtos(Page<Question> page) {

        List<QuestionDetailDto> content = entities_to_dtos(page.getContent());

        return PageableExecutionUtils.getPage(content, page.getPageable(), page::getTotalElements);
    }
}
