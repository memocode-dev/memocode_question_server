package dev.memocode.question_server.usecase;

import dev.memocode.question_server.base.BaseTest;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

import static dev.memocode.question_server.support.TestConstructorFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class QuestionUseCaseTest extends BaseTest {

    @Autowired
    private QuestionUseCase questionUseCase;

    @DisplayName("질문 생성 테스트")
    @Test
    void createQuestion() {
        //given
        Set<String> tags = Set.of("tag1", "tag2");

        QuestionCreateDto questionCreateDto = createQuestionCreateDto("created_title", "created_content", author.getId(), tags);

        //when
        UUID created_question_id = questionUseCase.createQuestion(questionCreateDto);

        //then
        QuestionDetailDto question = questionUseCase.findQuestion(created_question_id);
        assertThat(question)
                .extracting(QuestionDetailDto::getTitle, QuestionDetailDto::getContent)
                .containsExactly("created_title", "created_content");
    }

    @DisplayName("질문 수정 테스트")
    @Test
    void updateQuestion() {
        //given
        Set<String> tags = Set.of("tag1", "tag2");

        QuestionCreateDto questionCreateDto = createQuestionCreateDto("title", "content", author.getId(), tags);

        UUID questionId = questionUseCase.createQuestion(questionCreateDto);

        QuestionUpdateDto questionUpdateDto = createQuestionUpdateDto(questionId, "updated_title", "updated_content", author.getId());
        //when
        UUID updated_question_id = questionUseCase.updateQuestion(questionUpdateDto);

        //then
        QuestionDetailDto question = questionUseCase.findQuestion(updated_question_id);
        assertThat(question)
                .extracting(QuestionDetailDto::getTitle, QuestionDetailDto::getContent)
                .containsExactly("updated_title", "updated_content");
    }

    @DisplayName("질문 삭제 테스트")
    @Test
    void deleteQuestion() {
        //given
        Set<String> tags = Set.of("tag1", "tag2");

        QuestionCreateDto questionCreateDto = createQuestionCreateDto("title", "content", author.getId(), tags);

        UUID questionId = questionUseCase.createQuestion(questionCreateDto);

        QuestionDeleteDto questionDeleteDto = createQuestionDeleteDto(questionId, author.getId());

        //when
        questionUseCase.deleteQuestion(questionDeleteDto);

        //then
        assertThatThrownBy(() -> questionUseCase.findQuestion(questionId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("해당글이 존재하지 않습니다.");
    }

    @DisplayName("질문 단일 조회 테스트")
    @Test
    void findQuestion() {
        //given
        Set<String> tags = Set.of("tag1", "tag2");

        QuestionCreateDto questionCreateDto = createQuestionCreateDto("title", "content", author.getId(), tags);

        UUID questionId = questionUseCase.createQuestion(questionCreateDto);

        //when
        QuestionDetailDto question = questionUseCase.findQuestion(questionId);

        //then
        assertThat(question.getId()).isEqualTo(questionId);
        assertThat(question.getTitle()).isEqualTo("title");
        assertThat(question.getContent()).isEqualTo("content");
        assertThat(question.getTags()).containsExactlyInAnyOrderElementsOf(tags);
    }

    @DisplayName("질문 전체 조회 테스트")
    @Test
    void findAllQuestion() {
        //given
        IntStream.range(0, 20).forEach(i -> {
            QuestionCreateDto questionCreateDto = createQuestionCreateDto("title" + i, "content" + i, author.getId(),Set.of("tag" + i));
            questionUseCase.createQuestion(questionCreateDto);
        });

        //when
        Page<QuestionDetailDto> questions = questionUseCase.findAllQuestion(Pageable.ofSize(10));

        //then
        assertThat(questions.getTotalElements()).isEqualTo(20); // 전체 요소 수 검증
        assertThat(questions.getTotalPages()).isEqualTo(2); // 전체 페이지 수 검증
        assertThat(questions.getSize()).isEqualTo(10); // 페이지 크기 검증
        assertThat(questions.getContent()).hasSize(10); // 실제 컨텐츠의 크기 검증
    }
}