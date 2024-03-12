package dev.memocode.question_server.domain.question.entity;

import dev.memocode.question_server.domain.base.base.entity.AggregateRoot;
import dev.memocode.question_server.domain.external.author.entity.Author;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Table(name = "questions")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Question extends AggregateRoot {

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "affinity")
    @Builder.Default
    private Integer affinity = 0;

    @ManyToOne(fetch = LAZY)
    private Author author;

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }
}
