package dev.memocode.question_server.api;

import dev.memocode.question_server.api.spec.QuestionApi;
import dev.memocode.question_server.domain.usecase.QuestionUseCase;
import dev.memocode.question_server.dto.form.QuestionCreateForm;
import dev.memocode.question_server.dto.form.QuestionUpdateForm;
import dev.memocode.question_server.dto.request.QuestionCreateDto;
import dev.memocode.question_server.dto.response.QuestionDetailDto;
import dev.memocode.question_server.dto.response.QuestionUpdateDto;
import dev.memocode.question_server.dto.response.QuestionsDto;
import dev.memocode.question_server.mapper.QuestionCreateDtoMapper;
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
    private final QuestionCreateDtoMapper questionCreateDtoMapper;
    private static final String ACCOUNT_ID_CLAIM_NAME = "account_id";

    /**
     * QNA 글 생성
     */
    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionCreateForm form,@AuthenticationPrincipal Jwt jwt) {
        QuestionCreateDto questionCreateDto = questionCreateDtoMapper.fromQuestionCreateFormAndAccountId(form,
                UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        UUID questionId = questionUseCase.createQuestion(questionCreateDto);
        return ResponseEntity.created(URI.create(questionId.toString())).body(questionId.toString());
    }
    /**
     * QNA 글 삭제
     */
    @DeleteMapping("{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId, @AuthenticationPrincipal Jwt jwt) {
        return null;
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
