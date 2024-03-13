package dev.memocode.question_server.api.spec;

import dev.memocode.question_server.domain.question.dto.form.QuestionCreateForm;
import dev.memocode.question_server.domain.question.dto.form.QuestionUpdateForm;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

@Tag(name = "questions", description = "QNA API")
public interface QuestionApi {

    @Operation(summary = "QNA 생성")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<String> createQuestion(QuestionCreateForm form, Jwt jwt);

    @Operation(summary = "QNA 삭제")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<Void> deleteQuestion(UUID questionId, Jwt jwt);

    @Operation(summary = "QNA 수정")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<String> updateQuestion(UUID questionId, QuestionUpdateForm form, Jwt jwt);

    @Operation(summary = "QNA 단일 조회")
    ResponseEntity<QuestionDetailDto> findQuestion(Long questionId);

    @Operation(summary = "QNA 전체 조회")
    ResponseEntity<QuestionsDto> findAllQuestion(Jwt jwt, int page, int size);
}