package dev.memocode.question_server.domain.question.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.memocode.question_server.domain.external.author.dto.AuthorDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.entity.QQuestion;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepositoryCustom;
import dev.memocode.question_server.domain.tag.dto.response.QuestionTagDto;
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
    public Page<Question> findAllQuestion(Pageable pageable) {

        List<Question> content = queryFactory
                .selectFrom(question)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(question.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(question.count())
                .from(question);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }
}
