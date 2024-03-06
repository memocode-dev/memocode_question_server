package dev.memocode.question_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuestionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionServerApplication.class, args);
    }

}
