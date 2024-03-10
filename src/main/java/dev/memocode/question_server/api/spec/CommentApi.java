package dev.memocode.question_server.api.spec;

import dev.memocode.question_server.domain.comment.dto.form.CommentCreateForm;
import dev.memocode.question_server.domain.comment.dto.form.CommentUpdateForm;
import dev.memocode.question_server.domain.comment.dto.response.CommentUpdateDto;
import dev.memocode.question_server.domain.comment.dto.response.CommentsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

@Tag(name = "comments", description = "COMMENT API")
public interface CommentApi {

    @Operation(summary = "COMMENT 생성")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<String> createComment(CommentCreateForm form, Jwt jwt);

    @Operation(summary = "COMMENT 삭제")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<Void> deleteComment(Long commentId, Jwt jwt);

    @Operation(summary = "COMMENT 수정")
    @SecurityRequirement(name = "bearer-key")
    ResponseEntity<CommentUpdateDto> updateComment(Long commentId, CommentUpdateForm form, Jwt jwt);

    @Operation(summary = "QUESTION에 해당하는 COMMENT 조회")
    ResponseEntity<CommentsDto> findQuestionComment(Long questionId);
}