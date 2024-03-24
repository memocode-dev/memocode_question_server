package dev.memocode.question_server.usecase;

import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.external.author.repository.AuthorRepository;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.service.QuestionService;
import dev.memocode.question_server.domain.tag.dto.request.TagCreateDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class QuestionUseCaseTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("질문 생성 테스트")
    @Test
    void createQuestion() {
        //given
        Author author = authorRepository.save(Author.builder()
                .username("test")
                .nickname("test")
                .build());
        HashSet<TagCreateDto> tags = new HashSet<>(List.of(new TagCreateDto("tag1"), new TagCreateDto("tag2")));
        QuestionCreateDto questionCreateDto = QuestionCreateDto.builder()
                .title("title")
                .content("content")
                .userId(author.getId())
                .tags(tags)
                .build();
        //when
        questionService.createQuestion(questionCreateDto);
        //then
        assertThat(questionService.findAllQuestion(Pageable.ofSize(10)).getContent().size()).isEqualTo(1);
    }
}