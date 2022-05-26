-- 用户表
CREATE TABLE USER(
    ID                  BIGINT       NOT NULL,
    ACCOUNT             VARCHAR(255) NOT NULL,
    PASS_WORD           VARCHAR(255) NOT NULL,
    NAME                VARCHAR(255) NOT NULL,
    EMAIL               VARCHAR(255) NULL,
    TELEPHONE           VARCHAR(255) NULL,
    STATUS              INT          NULL,
    LAST_LOGIN_TIME     BIGINT       NULL,
    CREATOR_ID          BIGINT       NULL,
    CREATOR_ACCOUNT     VARCHAR(255) NULL,
    CREATOR_NAME        VARCHAR(255) NULL,
    MODIFY_USER_ID      BIGINT       NULL,
    MODIFY_USER_ACCOUNT VARCHAR(255) NULL,
    MODIFY_USER_NAME    VARCHAR(255) NULL,
    CREATION_TIME       BIGINT       NULL,
    LAST_MODIFY_TIME    BIGINT       NULL,
    DELETED             INT          NULL,
    CONSTRAINT PK_USER PRIMARY KEY (ID)
);

CREATE TABLE USER_DETAIL(
    ID      BIGINT       NOT NULL,
    DETAIL  VARCHAR(255) NULL,
    CONSTRAINT PK_USER_DETAIL PRIMARY KEY (ID)
);

ALTER TABLE USER_DETAIL
    ADD CONSTRAINT FK_USER_DETAIL_ON_USER FOREIGN KEY (ID) REFERENCES USER (ID);