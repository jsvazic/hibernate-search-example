SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `exampledb` ;
CREATE SCHEMA IF NOT EXISTS `exampledb` DEFAULT CHARACTER SET utf8 ;
USE `exampledb` ;

-- -----------------------------------------------------
-- Table `exampledb`.`division`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`division` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`division` (
  `id` BIGINT NOT NULL ,
  `create_date` DATETIME NULL ,
  `last_modify_date` DATETIME NULL ,
  `state` TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`subordinate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`subordinate` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`subordinate` (
  `id` BIGINT NOT NULL ,
  `division_id` BIGINT NOT NULL ,
  `create_date` DATETIME NULL ,
  `last_modify_date` DATETIME NULL ,
  `state` TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`motif`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`motif` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`motif` (
  `id` BIGINT NOT NULL ,
  `subordinate_id` BIGINT NOT NULL ,
  `create_date` DATETIME NULL ,
  `last_modify_date` DATETIME NULL ,
  `state` TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`inquiry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`inquiry` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`inquiry` (
  `id` BIGINT NOT NULL ,
  `motif_id` BIGINT NOT NULL ,
  `level` INT NOT NULL ,
  `create_date` DATETIME NULL ,
  `last_modify_date` DATETIME NULL ,
  `state` TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`sequence_generator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`sequence_generator` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`sequence_generator` (
  `sequence_name` VARCHAR(255) NOT NULL ,
  `sequence_next_hi_value` BIGINT NULL ,
  PRIMARY KEY (`sequence_name`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`division_text`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`division_text` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`division_text` (
  `id` BIGINT NOT NULL ,
  `state` TINYINT NULL DEFAULT 1 ,
  `language` VARCHAR(5) NOT NULL ,
  `text_value` TEXT NULL ,
  `division_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`subordinate_text`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`subordinate_text` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`subordinate_text` (
  `id` BIGINT NOT NULL ,
  `state` TINYINT NULL DEFAULT 1 ,
  `language` VARCHAR(5) NOT NULL ,
  `text_value` TEXT NULL ,
  `subordinate_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`motif_text`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`motif_text` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`motif_text` (
  `id` BIGINT NOT NULL ,
  `state` TINYINT NULL DEFAULT 1 ,
  `language` VARCHAR(5) NOT NULL ,
  `text_value` TEXT NULL ,
  `motif_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`inquiry_content_text`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`inquiry_content_text` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`inquiry_content_text` (
  `id` BIGINT NOT NULL ,
  `state` TINYINT NULL DEFAULT 1 ,
  `language` VARCHAR(5) NOT NULL ,
  `text_value` TEXT NULL ,
  `inquiry_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exampledb`.`inquiry_reason_text`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exampledb`.`inquiry_reason_text` ;

CREATE  TABLE IF NOT EXISTS `exampledb`.`inquiry_reason_text` (
  `id` BIGINT NOT NULL ,
  `state` TINYINT NULL DEFAULT 1 ,
  `language` VARCHAR(5) NOT NULL ,
  `text_value` TEXT NULL ,
  `inquiry_id` BIGINT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO division (id) VALUES (1);
INSERT INTO subordinate (id, division_id) VALUES (1,1);
INSERT INTO motif (id, subordinate_id) VALUES (1,1);
INSERT INTO inquiry (id, motif_id, level) VALUES (1,1,3);

INSERT INTO division_text (id, language, division_id, text_value) VALUES (1,'EN',1,'Division');
INSERT INTO division_text (id, language, division_id, text_value) VALUES (2,'FR',1,'FRENCH - Division');
INSERT INTO division_text (id, language, division_id, text_value) VALUES (3,'SP',1,'SPANSIH - Division');

INSERT INTO subordinate_text (id, language, subordinate_id, text_value) VALUES (1,'EN',1,'subordinate');
INSERT INTO subordinate_text (id, language, subordinate_id, text_value) VALUES (2,'FR',1,'FRENCH - subordinate');
INSERT INTO subordinate_text (id, language, subordinate_id, text_value) VALUES (3,'SP',1,'SPANSIH - subordinate');

INSERT INTO motif_text (id, language, motif_id, text_value) VALUES (1,'EN',1,'motif');
INSERT INTO motif_text (id, language, motif_id, text_value) VALUES (2,'FR',1,'FRENCH - motif');
INSERT INTO motif_text (id, language, motif_id, text_value) VALUES (3,'SP',1,'SPANSIH - motif');

INSERT INTO inquiry_content_text (id, language, inquiry_id, text_value) VALUES (1,'EN',1,'inquiry text');
INSERT INTO inquiry_content_text (id, language, inquiry_id, text_value) VALUES (2,'FR',1,'FRENCH - inquiry text');
INSERT INTO inquiry_content_text (id, language, inquiry_id, text_value) VALUES (3,'FR',1,'SPANSIH - inquiry text');

INSERT INTO inquiry_reason_text (id, language, inquiry_id, text_value) VALUES (1,'EN',1,'Reason text');
INSERT INTO inquiry_reason_text (id, language, inquiry_id, text_value) VALUES (2,'FR',1,'FRENCH - Reason text');
INSERT INTO inquiry_reason_text (id, language, inquiry_id, text_value) VALUES (3,'FR',1,'SPANSIH - Reason text');