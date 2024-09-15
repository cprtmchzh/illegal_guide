CREATE TABLE member
(
    member_id       SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    member_nickname VARCHAR(10)  NOT NULL,
    member_email    VARCHAR(30)  NOT NULL,
    member_password VARCHAR(255) NOT NULL,
    member_role     VARCHAR(8)   NOT NULL
);

CREATE TABLE post
(
    post_id        SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    post_title     VARCHAR(50)   NOT NULL,
    post_user      VARCHAR(20)   NOT NULL,
    post_content   VARCHAR(500) NOT NULL,
    post_hits      SMALLINT      NOT NULL,
    post_timestamp TIMESTAMP     NOT NULL
);

CREATE TABLE comment
(
    comment_id        SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    comment_post      SMALLINT     NOT NULL,
    comment_user      VARCHAR(10)  NOT NULL,
    comment_content   VARCHAR(100) NOT NULL,
    comment_timestamp TIMESTAMP    NOT NULL
);