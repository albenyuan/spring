CREATE DATABASE mybatis;


DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  id       INT(11)      AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(32)  DEFAULT '',
  password VARCHAR(255) DEFAULT '',
  name     VARCHAR(32)  DEFAULT '',
  email    VARCHAR(32)  DEFAULT ''
);

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  id      INT(11) AUTO_INCREMENT PRIMARY KEY,
  user_id INT(11),
  role_id INT(11)
);

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  id   INT(2)      AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(16) DEFAULT '',
  code VARCHAR(16) DEFAULT ''
);

INSERT INTO `user` (`id`, `username`, `password`, `name`, `email`) VALUES
  (1, 'albenyuan', 'password', 'Alben Yuan', 'albenyuan@aliyun.com');

INSERT INTO `role` (`id`, `name`, `code`) VALUES
  (1, '管理员', 'admin');

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
  (1, 1, 1);