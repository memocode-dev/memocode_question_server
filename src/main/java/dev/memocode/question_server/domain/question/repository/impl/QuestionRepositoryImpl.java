package dev.memocode.question_server.domain.question.repository.impl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.memocode.question_server.domain.external.author.dto.AuthorDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.entity.QQuestion;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepositoryCustom;
import dev.memocode.question_server.domain.tag.dto.response.QuestionTagDto;
import dev.memocode.question_server.domain.tag.dto.response.TagsDto;
import dev.memocode.question_server.domain.tag.entity.QQuestionTag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

import static dev.memocode.question_server.domain.question.entity.QQuestion.question;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Question> findById(UUID id) {
        return Optional.ofNullable(queryFactory.selectFrom(question)
                .where(
                        question.id.eq(id),
                        question.deleted.isFalse()
                ).fetchOne());
    }


    @Override
    public Page<QuestionDetailDto> findAllQuestion(Pageable pageable) {
        List<Question> questions = fetchQuestionsWithAuthorInfo(pageable);
        Map<UUID, List<String>> questionTagsMap = fetchTagNamesForQuestions(questions);

        List<QuestionDetailDto> dtos = questions.stream()
                .map(question -> toQuestionDetailDto(question, questionTagsMap))
                .collect(Collectors.toList());

        long total = fetchTotalQuestionCount();

        return new PageImpl<>(dtos, pageable, total);
    }

    private List<Question> fetchQuestionsWithAuthorInfo(Pageable pageable) {
        return queryFactory
                .selectFrom(QQuestion.question)
                .join(QQuestion.question.author).fetchJoin()
                .where(QQuestion.question.deleted.isFalse())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private Map<UUID, List<String>> fetchTagNamesForQuestions(List<Question> questions) {
        List<QuestionTagDto> tagDtos = queryFactory
                .select(Projections.constructor(QuestionTagDto.class,
                        QQuestionTag.questionTag.question.id,
                        QQuestionTag.questionTag.tag.name))
                .from(QQuestionTag.questionTag)
                .join(QQuestionTag.questionTag.tag)
                .where(QQuestionTag.questionTag.question.id.in(questions.stream().map(Question::getId).collect(Collectors.toList())))
                .fetch();

        return tagDtos.stream()
                .collect(Collectors.groupingBy(
                        QuestionTagDto::getQuestionId,
                        Collectors.mapping(QuestionTagDto::getTagName, Collectors.toList())
                ));
    }

    private QuestionDetailDto toQuestionDetailDto(Question question, Map<UUID, List<String>> questionTagsMap) {
        List<String> tags = questionTagsMap.getOrDefault(question.getId(), Collections.emptyList());
        return QuestionDetailDto.builder()
                .questionId(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .affinity(question.getAffinity())
                .createdAt(question.getCreatedAt())
                .tags(TagsDto.builder().tags(tags).build())
                .author(AuthorDto.builder()
                        .authorId(question.getAuthor().getId())
                        .nickname(question.getAuthor().getNickname())
                        .build())
                .build();
    }

    private long fetchTotalQuestionCount() {
        return Optional.ofNullable(queryFactory
                        .select(QQuestion.question.count())
                        .from(QQuestion.question)
                        .where(QQuestion.question.deleted.isFalse())
                        .fetchOne())
                .orElse(0L);
    }

}
