package dev.memocode.question_server.domain.qna;

import dev.memocode.question_server.domain.base.base.entity.AggregateRoot;
import dev.memocode.question_server.domain.external.external.user.entity.Author;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Table(name = "articles")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Question extends AggregateRoot {

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = LAZY)
    private Author author;
}
