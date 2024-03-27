package dev.memocode.question_server.domain.question.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
