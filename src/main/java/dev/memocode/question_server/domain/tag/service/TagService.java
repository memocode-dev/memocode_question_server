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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
@Slf4j
public class TagService {

    private final TagRepository tagRepository;
    private final QuestionTagRepository questionTagRepository;

    public void createTag(Question question, Set<TagCreateDto> tags) {
        questionTagRepository.saveAll(tags.stream()
                .map(dto -> tagRepository.findByName(dto.getName())
                        .orElseGet(() -> tagRepository.save(Tag.builder().name(dto.getName()).build())))
                .map(tag -> QuestionTag.builder()
                        .question(question)
                        .tag(tag)
                        .build())
                .collect(toList()));
    }
}
