/*CREATE SCHEMA `listexpenses` DEFAULT CHARACTER SET utf8 ;

CREATE  TABLE IF NOT EXISTS `listexpenses`.`receivers` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE IF NOT EXISTS `listexpenses`.`expenses` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `paydate` DATE NULL ,
  `receiver_id` INT NOT NULL,
  `value` DECIMAL ,
  PRIMARY KEY (`id`) );*/