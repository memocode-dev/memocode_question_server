package dev.memocode.question_server.api;

import dev.memocode.question_server.api.spec.QuestionApi;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.usecase.QuestionUseCase;
import dev.memocode.question_server.domain.question.dto.form.QuestionCreateForm;
import dev.memocode.question_server.domain.question.dto.form.QuestionUpdateForm;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.dto.request.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.mapper.QuestionDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/questions")
public class QuestionController implements QuestionApi {

    private final QuestionUseCase questionUseCase;
    private final QuestionDtoMapper questionDtoMapper;
    private static final String ACCOUNT_ID_CLAIM_NAME = "user_id";

    /**
     * QNA 글 생성
     */
    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionCreateForm form,@AuthenticationPrincipal Jwt jwt) {
        QuestionCreateDto questionCreateDto = questionDtoMapper.fromQuestionCreateFormAndUserId(form,
                UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        UUID createdQuestion = questionUseCase.createQuestion(questionCreateDto);
        return ResponseEntity.created(URI.create(createdQuestion.toString())).body(createdQuestion.toString());
    }
    /**
     * QNA 글 삭제
     */
    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID questionId, @AuthenticationPrincipal Jwt jwt) {
        QuestionDeleteDto questionDeleteDto = questionDtoMapper.fromQuestionDeleteFormAndAccountId(questionId,
                UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        questionUseCase.deleteQuestion(questionDeleteDto);
        return ResponseEntity.noContent().build();
    }
    /**
     * QNA 글 수정
     */
    @PatchMapping("/{questionId}")
    public ResponseEntity<String> updateQuestion(@PathVariable UUID questionId, @RequestBody QuestionUpdateForm form, @AuthenticationPrincipal Jwt jwt) {
        QuestionUpdateDto questionUpdateDto = questionDtoMapper.fromQuestionUpdateFormAndAccountId(questionId,
                form,
                UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        UUID updatedQuestion = questionUseCase.updateQuestion(questionUpdateDto);
        return ResponseEntity.ok().body(updatedQuestion.toString());
    }
    /**
     * QNA 글 단일 조회
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionDetailDto> findQuestion(@PathVariable Long questionId) {
        return null;
    }
    /**
     * QNA 글 전체 조회
     */
    @GetMapping
    public ResponseEntity<Page<QuestionDetailDto>> findAllQuestion(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<QuestionDetailDto> allQuestion = questionUseCase.findAllQuestion(pageable);
        return ResponseEntity.ok().body(allQuestion);
    }
}
