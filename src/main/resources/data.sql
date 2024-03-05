DROP TABLE IF EXISTS question_tag;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS questions;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id         CHAR(36)            NOT NULL PRIMARY KEY,
    username   VARCHAR(255) UNIQUE NOT NULL,
    nickname   VARCHAR(255)        NOT NULL,
    account_id CHAR(36)            NOT NULL,
    created_at DATETIME            NOT NULL,
    updated_at DATETIME            NOT NULL,
    deleted_at DATETIME,
    is_deleted BOOLEAN             NOT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE questions
(
    id             CHAR(36) PRIMARY KEY,
    title          VARCHAR(255),
    content        LONGTEXT,
    author_id      CHAR(36),
    affinity       INT,
    created_at     DATETIME NOT NULL,
    updated_at     DATETIME NOT NULL,
    deleted_at     DATETIME,
    is_deleted     BOOLEAN  NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE tags
(
    id         CHAR(36) PRIMARY KEY,
    name       VARCHAR(255) UNIQUE NOT NULL,
    created_at DATETIME            NOT NULL,
    updated_at DATETIME            NOT NULL,
    deleted_at DATETIME,
    is_deleted BOOLEAN             NOT NULL
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE question_tag
(
    id         CHAR(36) PRIMARY KEY,
    question_id CHAR(36),
    tag_id     CHAR(36),
    created_at DATETIME            NOT NULL,
    updated_at DATETIME            NOT NULL
    FOREIGN KEY (question_id) REFERENCES questions (id),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE comments
(
    id         CHAR(36) PRIMARY KEY,
    content    LONGTEXT,
    author_id  CHAR(36),
    question_id CHAR(36),
    parent_comment_id CHAR(36),
    created_at DATETIME            NOT NULL,
    updated_at DATETIME            NOT NULL,
    deleted_at DATETIME,
    is_deleted BOOLEAN             NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id),
    FOREIGN KEY (question_id) REFERENCES questions (id),
    FOREIGN KEY (parent_comment_id) REFERENCES comments (id)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;