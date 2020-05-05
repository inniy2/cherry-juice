DROP TABLE IF EXISTS `test`.`ghost_user`;
CREATE TABLE `test`.`ghost_user` (
  `user_id` int unsigned not null auto_increment,
  `user_name` varchar(20) not null,
  `password` varchar(20) not null,
primary key(`user_id`)
);

DROP TABLE IF EXISTS `test`.`ghost_lock`;
CREATE TABLE `test`.`ghost_lock` (
  `lock_id` int unsigned not null auto_increment,
  `ghost_host` varchar(20) not null,
  `envoy_port` varchar(5) not null,
  `user_name` varchar(20) not null,
  `lock_status` varchar(20) not null,
primary key(`lock_id`),
unique key uix_ghost_host (`ghost_host`)
);

DROP TABLE IF EXISTS `test`.`test_tbl1`;
CREATE TABLE `test_tbl1` (
  `a` int NOT NULL AUTO_INCREMENT,
  `b` int DEFAULT NULL,
  `c` int DEFAULT NULL,
  PRIMARY KEY (`a`)
);


drop table if exists _test_tbl1_ghc;
drop table if exists _test_tbl1_gho;

