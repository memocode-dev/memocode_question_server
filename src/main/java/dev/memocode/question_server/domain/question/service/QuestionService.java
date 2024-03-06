package dev.memocode.question_server.domain.question.service;

import dev.memocode.question_server.domain.user.entity.Author;
import dev.memocode.question_server.domain.user.service.AuthorService;
import dev.memocode.question_server.domain.question.entity.Question;
import dev.memocode.question_server.domain.question.repository.QuestionRepository;
import dev.memocode.question_server.dto.request.QuestionCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final AuthorService authorService;

    public Question createQuestion(QuestionCreateDto questionCreateDto) {
        Author author = authorService.findByIdElseThrow(questionCreateDto.getAccountId());
        Question question = Question.builder()
                .title(questionCreateDto.getTitle())
                .content(questionCreateDto.getContent())
                .author(author)
                .build();
        return questionRepository.save(question);
    }
}
