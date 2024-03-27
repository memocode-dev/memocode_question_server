package dev.memocode.question_server.support;

import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TestConstructorFactory {

    public static QuestionCreateDto createQuestionCreateDto(String title, String content, UUID userId, Set<String> tags) {
        return QuestionCreateDto.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .tags(tags)
                .build();
    }

    public static QuestionUpdateDto createQuestionUpdateDto(UUID questionId, String title, String content, UUID userId) {
        return QuestionUpdateDto.builder()
                .questionId(questionId)
                .title(title)
                .content(content)
                .userId(userId)
                .build();
    }

    public static QuestionDeleteDto createQuestionDeleteDto(UUID questionId, UUID userId) {
        return QuestionDeleteDto.builder()
                .questionId(questionId)
                .userId(userId)
                .build();
    }
}
