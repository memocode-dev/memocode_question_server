package dev.memocode.question_server.base;

import dev.memocode.question_server.domain.external.author.entity.Author;
import dev.memocode.question_server.domain.external.author.repository.AuthorRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.time.Instant;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(value = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BaseTest {

    protected final static String MYSQL_DATABASE = "testdb";
    protected final static String MYSQL_USERNAME = "test";
    protected final static String MYSQL_PASSWORD = "test";
    protected static String MYSQL_PORT;

    @ClassRule
    public static final Network network = Network.newNetwork();

    @ClassRule
    public final static MySQLContainer<?> mysql =
            new MySQLContainer<>(DockerImageName.parse("mysql:8.3.0"))
                    .withNetwork(network)
                    .withNetworkAliases("mysql")
                    .withDatabaseName(MYSQL_DATABASE)
                    .withUsername(MYSQL_USERNAME)
                    .withPassword(MYSQL_PASSWORD);

    public BaseTest() {
        mysql.start();

        MYSQL_PORT = String.valueOf(mysql.getMappedPort(3306));
    }

    @DynamicPropertySource
    static void mysqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.username", mysql::getUsername);
    }

    @Autowired
    private AuthorRepository authorRepository;

    protected Author author;

    @BeforeEach
    void beforeEach() {

        // 유저 생성
        this.author = Author.builder()
                .username("테스트이름")
                .nickname("테스트닉네임")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .deleted(false)
                .deletedAt(null)
                .build();
        authorRepository.save(this.author);
    }
}
