package dev.memocode.question_server.domain.question.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.memocode.question_server.domain.question.entity.QQuestion;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static dev.memocode.question_server.domain.question.entity.QQuestion.*;

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

}
