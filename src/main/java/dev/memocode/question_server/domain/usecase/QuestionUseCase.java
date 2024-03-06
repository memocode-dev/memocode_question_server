package dev.memocode.question_server.domain.usecase;

import dev.memocode.question_server.dto.request.QuestionCreateDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface QuestionUseCase {

    UUID createQuestion(QuestionCreateDto questionCreateDto);
}
