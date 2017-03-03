
CREATE DATABASE auditioner;
USER auditioner;

CREATE USER 'auditioner'@'localhost' IDENTIFIED BY 'Password1!';

GRANT ALL PRIVILEGES ON *.* TO 'auditioner'@'localhost'

CREATE TABLE `Family` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE `Production` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(48) NOT NULL,
  `auditionDate` date NOT NULL,
  `season` varchar(48) NOT NULL
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


CREATE TABLE `ProductionMember` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT
    `production`
)