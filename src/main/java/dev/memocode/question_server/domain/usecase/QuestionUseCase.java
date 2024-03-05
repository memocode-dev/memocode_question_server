package dev.memocode.question_server.domain.usecase;

import java.util.UUID;

public interface QuestionUseCase {

    UUID createQuestion(String title, String content);
}
