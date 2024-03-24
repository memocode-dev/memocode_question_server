package dev.memocode.question_server.domain.external.author.entity;

import dev.memocode.question_server.domain.base.base.entity.AggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Immutable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Immutable
@Entity
@SuperBuilder
@NoArgsConstructor(access = PROTECTED)
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Author extends AggregateRoot {

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "nickname")
    private String nickname;

}
