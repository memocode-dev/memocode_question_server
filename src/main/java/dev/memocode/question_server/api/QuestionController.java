package dev.memocode.question_server.api;

import dev.memocode.question_server.api.spec.QuestionApi;
import dev.memocode.question_server.domain.question.dto.request.QuestionDeleteDto;
import dev.memocode.question_server.domain.usecase.QuestionUseCase;
import dev.memocode.question_server.domain.question.dto.form.QuestionCreateForm;
import dev.memocode.question_server.domain.question.dto.form.QuestionUpdateForm;
import dev.memocode.question_server.domain.question.dto.request.QuestionCreateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionDetailDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionUpdateDto;
import dev.memocode.question_server.domain.question.dto.response.QuestionsDto;
import dev.memocode.question_server.domain.question.mapper.QuestionDtoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private static final String ACCOUNT_ID_CLAIM_NAME = "account_id";

    /**
     * QNA 글 생성
     */
    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionCreateForm form,@AuthenticationPrincipal Jwt jwt) {
        QuestionCreateDto questionCreateDto = questionDtoMapper.fromQuestionCreateFormAndAccountId(form,
                UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        UUID createdQuestion = questionUseCase.createQuestion(questionCreateDto);
        return ResponseEntity.created(URI.create(createdQuestion.toString())).body(createdQuestion.toString());
    }
    /**
     * QNA 글 삭제
     */
    @DeleteMapping("{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable UUID questionId, @AuthenticationPrincipal Jwt jwt) {
        QuestionDeleteDto questionDeleteDto = questionDtoMapper.fromQuestionDeleteFormAndAccountId(questionId,
                UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        questionUseCase.deleteQuestion(questionDeleteDto);
        return ResponseEntity.ok().build();
    }
    /**
     * QNA 글 수정
     */
    @PatchMapping("/{questionId}")
    public ResponseEntity<QuestionUpdateDto> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionUpdateForm form, @AuthenticationPrincipal Jwt jwt) {
        return null;
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
    public ResponseEntity<QuestionsDto> findAllQuestion(@AuthenticationPrincipal Jwt jwt,
                                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                                        @RequestParam(name = "size", defaultValue = "10") int size) {
        return null;
    }
}
