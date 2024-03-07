package dev.memocode.question_server.api;

import dev.memocode.question_server.api.spec.CommentApi;
import dev.memocode.question_server.dto.form.CommentCreateForm;
import dev.memocode.question_server.dto.form.CommentUpdateForm;
import dev.memocode.question_server.dto.response.CommentUpdateDto;
import dev.memocode.question_server.dto.response.CommentsDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")
public class CommentController implements CommentApi {

    /**
     * COMMENT 생성
     * */
    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentCreateForm form, @AuthenticationPrincipal Jwt jwt) {
        return null;
    }

    /**
     * COMMENT 삭제
     * */
    @DeleteMapping("{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal Jwt jwt) {
        return null;
    }

    /**
     * COMMENT 수정
     * */
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentUpdateDto> updateComment(@PathVariable Long commentId,
                                                          CommentUpdateForm form,
                                                          @AuthenticationPrincipal Jwt jwt) {
        return null;
    }

    /**
     * QUESTION에 해당하는 COMMENT 조회
     * */
    @GetMapping("/{questionId}")
    public ResponseEntity<CommentsDto> findQuestionComment(@PathVariable Long questionId) {
        return null;
    }
}
