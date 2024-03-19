package dev.memocode.question_server.domain.tag.service;

import dev.memocode.question_server.domain.tag.dto.request.TagCreateDto;
import dev.memocode.question_server.domain.tag.entity.Tag;
import dev.memocode.question_server.domain.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class TagService {

    private final TagRepository tagRepository;

    public List<Tag> createTag(Set<TagCreateDto> tags) {
        return tags.stream()
                .map(tagCreateDto -> tagRepository.findByName(tagCreateDto.getName())
                        .orElseGet(() -> tagRepository.save(Tag.builder()
                                .name(tagCreateDto.getName())
                                .build())))
                .collect(Collectors.toList());
    }
}
