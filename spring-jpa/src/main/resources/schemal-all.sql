CREATE DATABASE IF NOT EXISTS `spring_jpa`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id`       BIGINT AUTO_INCREMENT,
  `username` VARCHAR(16),
  `password` VARCHAR(32),
  `email`    VARCHAR(32),
  `phone`    VARCHAR(16),
  PRIMARY KEY (`id`)
);

INSERT INTO `user` (`id`, `username`, `password`, `email`, `phone`)
VALUES
  (1, 'albenyuan', '123456', 'albenyuan@aliyun.com', '13212341234'),
  (2, 'wen_job', '123456', 'wen_job@163.com', '13212341234');


DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `id`       BIGINT AUTO_INCREMENT,
  `user_id`  BIGINT,
  `name`     VARCHAR(32),
  `province` VARCHAR(8),
  `city`     VARCHAR(32),
  `area`     VARCHAR(32),
  `detail`   VARCHAR(255),
  PRIMARY KEY (`id`)
);

INSERT INTO `address` (`user_id`, `name`, `province`, `city`, `area`, `detail`)
VALUES
  (1, '家', '上海', '上海', '普陀区', '长风公园'),
  (1, '公司', '上海', '上海', '普陀区', '天地软件园'),
  (2, '住处', '上海', '上海', '普陀区', '曹杨'),
  (2, '公司', '上海', '上海', '普陀区', '大渡河路天地软件园');




