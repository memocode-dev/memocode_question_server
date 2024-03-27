package dev.memocode.question_server.domain.tag.service;

import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.tag.dto.request.TagCreateDto;
import dev.memocode.question_server.domain.tag.entity.QuestionTag;
import dev.memocode.question_server.domain.tag.entity.Tag;
import dev.memocode.question_server.domain.tag.repository.QuestionTagRepository;
import dev.memocode.question_server.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class TagService {

    private final TagRepository tagRepository;

    public Tag createTag(String name) {

        validateCreateTag(name);

        Tag tag = Tag.builder()
                .name(name)
                .build();

        return tagRepository.save(tag);
    }

    public Tag findTagOrCreateTagByName(String name) {

        Optional<Tag> _tag = findByName(name);

        if (_tag.isPresent()) {
            _tag.get();
        }

        return createTag(name);
    }

    public Set<Tag> findAllTagOrCreateTagByName(Set<String> tagNames) {
        return tagNames.stream()
                .map(this::findTagOrCreateTagByName)
                .collect(Collectors.toSet());
    }

    private Optional<Tag> findByName(String name) {
        return tagRepository.findByName(name);
    }

    private void validateCreateTag(String name) {
        Optional<Tag> _tag = findByName(name);

        if (_tag.isPresent()) {
            throw new GlobalException(TAG_ALREADY_EXISTS);
        }
    }
}
