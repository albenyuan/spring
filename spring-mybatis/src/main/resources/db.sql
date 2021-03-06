# CREATE DATABASE mybatis;


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


DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  id        INT(11)      AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(16)  DEFAULT '',
  url       VARCHAR(255) DEFAULT '',
  icon      VARCHAR(32)  DEFAULT '',
  parent_id INT(11)      DEFAULT -1,
  enabled   BIT(1)       DEFAULT FALSE
);

INSERT INTO `user` (`id`, `username`, `password`, `name`, `email`) VALUES
  (1, 'albenyuan', 'password', 'Alben Yuan', 'albenyuan@aliyun.com');

INSERT INTO `role` (`id`, `name`, `code`) VALUES
  (1, '管理员', 'admin');

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
  (1, 1, 1);

INSERT INTO `menu` (id, name, url, icon, parent_id, enabled) VALUES
  (1, '权限管理', '', 'fa fa-config', -1, 1),
  (2, '角色管理', '/system/role', 'fa fa-config', 1, 1),
  (3, '角色添加', '/system/role/add', 'fa fa-plus', 2, 1),
  (4, '菜单管理', '/system/menu', 'fa fa-config', 1, 1),
  (5, '用户管理', '/system/user', 'fa fa-config', 1, 1),
  (6, '用户信息', '/user/list', 'fa fa-config', -1, 1),
  (7, '角色信息', '/role/list', 'fa fa-config', -1, 1);