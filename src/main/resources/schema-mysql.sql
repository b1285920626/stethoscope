# 数据库初始化,没写呢
DROP TABLE IF EXISTS tb_user;

CREATE TABLE tb_user
(
    id                INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_name         VARCHAR(32),
    password          VARCHAR(32),
    email             VARCHAR(32),
    create_time       TIMESTAMP,
    last_login_time   TIMESTAMP,
    is_effective      BIT,
    limits            INT(5)
)  DEFAULT CHARSET = utf8;