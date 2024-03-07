package dev.memocode.question_server.api.spec;

import dev.memocode.question_server.dto.form.QuestionCreateForm;
import dev.memocode.question_server.dto.form.QuestionUpdateForm;
import dev.memocode.question_server.dto.response.QuestionUpdateDto;
import dev.memocode.question_server.dto.response.QuestionDetailDto;
import dev.memocode.question_server.dto.response.QuestionsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

@Tag(name = "questions", description = "QNA API")
public interface QuestionApi {

    @Operation(summary = "QNA 생성")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<String> createQuestion(QuestionCreateForm form, Jwt jwt);

    @Operation(summary = "QNA 삭제")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<Void> deleteQuestion(Long questionId, Jwt jwt);

    @Operation(summary = "QNA 수정")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<QuestionUpdateDto> updateQuestion(Long questionId, QuestionUpdateForm form, Jwt jwt);

    @Operation(summary = "QNA 단일 조회")
    ResponseEntity<QuestionDetailDto> findQuestion(Long questionId);

    @Operation(summary = "QNA 전체 조회")
    ResponseEntity<QuestionsDto> findAllQuestion(Jwt jwt, int page, int size);
}