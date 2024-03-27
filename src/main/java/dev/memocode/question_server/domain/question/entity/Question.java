package dev.memocode.question_server.domain.question.entity;

import dev.memocode.question_server.domain.base.base.entity.AggregateRoot;
import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.tag.entity.QuestionTag;
import dev.memocode.question_server.domain.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Table(name = "questions")
@SQLRestriction("is_deleted = false")
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

    @OneToMany(mappedBy = "question", cascade = PERSIST, orphanRemoval = true)
    @Builder.Default
    private Set<QuestionTag> questionTags = new HashSet<>();

    public void addTags(Set<Tag> tags) {
        tags.forEach(this::addTag);
    }

    public void addTag(Tag tag) {
        QuestionTag questionTag = QuestionTag.builder()
                .question(this)
                .tag(tag)
                .build();
        this.questionTags.add(questionTag);
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = Instant.now();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
