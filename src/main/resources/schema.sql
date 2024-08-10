CREATE TABLE member
(
    member_id       SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL PRIMARY KEY,
    member_nickname VARCHAR(10)  NOT NULL,
    member_email    VARCHAR(30)  NOT NULL,
    member_password VARCHAR(255) NOT NULL,
    member_role     VARCHAR(8)   NOT NULL
);