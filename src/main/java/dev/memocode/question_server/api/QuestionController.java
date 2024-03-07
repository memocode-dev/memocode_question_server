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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody QuestionCreateForm form,@AuthenticationPrincipal Jwt jwt) {
        QuestionCreateDto questionCreateDto = questionCreateDtoMapper.fromQuestionCreateFormAndAccountId(form,
                UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        UUID questionId = questionUseCase.createQuestion(questionCreateDto);
        return ResponseEntity.created(URI.create(questionId.toString())).body(questionId.toString());
    }

    @Override
    public ResponseEntity<Void> deleteQuestion(Long questionId, Jwt jwt) {
        return null;
    }

    @Override
    public ResponseEntity<QuestionUpdateDto> updateQuestion(Long questionId, QuestionUpdateForm form, Jwt jwt) {
        return null;
    }

    @Override
    public ResponseEntity<QuestionDetailDto> findQuestion(Long questionId) {
        return null;
    }

    @Override
    public ResponseEntity<QuestionsDto> findAllQuestion() {
        return null;
    }
}
