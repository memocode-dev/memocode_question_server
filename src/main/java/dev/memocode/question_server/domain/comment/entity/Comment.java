package dev.memocode.question_server.domain.comment.entity;

import dev.memocode.question_server.domain.base.base.entity.AggregateRoot;
import dev.memocode.question_server.domain.external.author.entity.Author;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Table(name = "comments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Comment extends AggregateRoot {

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    @Builder.Default
    private List<Comment> childComments = new ArrayList<>();
}
