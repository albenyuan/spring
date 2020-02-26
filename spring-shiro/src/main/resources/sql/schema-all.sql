CREATE DATABASE IF NOT EXISTS `spring_shiro`;
USE `spring_shiro`;


CREATE TABLE IF NOT EXISTS `user` (
  `id`       BIGINT AUTO_INCREMENT COMMENT '主键',
  `username` VARCHAR(16),
  `password` VARCHAR(64),
  `salt`     CHAR(8),
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`username`)
);

CREATE TABLE IF NOT EXISTS `role` (
  `id`   BIGINT AUTO_INCREMENT,
  `name` VARCHAR(32),
  `code` VARCHAR(8),
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `permission` (
  `id`   BIGINT AUTO_INCREMENT,
  `name` VARCHAR(32),
  `code` VARCHAR(8),
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `user_role` (
  `id`      BIGINT AUTO_INCREMENT,
  `user_id` BIGINT,
  `role_id` BIGINT,
  PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `role_permission` (
  `id`            BIGINT AUTO_INCREMENT,
  `role_id`       BIGINT,
  `permission_id` BIGINT,
  PRIMARY KEY (`id`)
);