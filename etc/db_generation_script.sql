-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema student_testing_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `student_testing_db` ;

-- -----------------------------------------------------
-- Schema student_testing_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `student_testing_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `student_testing_db` ;

-- -----------------------------------------------------
-- Table `mydb`.`correctAnswer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`correctAnswer` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`correctAnswer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(350) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `is_case_sensitive` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `student_testing_db` ;

-- -----------------------------------------------------
-- Table `student_testing_db`.`image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`image` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`image` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data` MEDIUMBLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `student_testing_db`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`role` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`role` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `student_testing_db`.`topic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`topic` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`topic` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `student_testing_db`.`test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`test` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`test` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `topic_id` INT(10) UNSIGNED NOT NULL,
  `description` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_test_topic1_idx` (`topic_id` ASC) VISIBLE,
  CONSTRAINT `fk_test_topic1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `student_testing_db`.`topic` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `student_testing_db`.`user_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`user_account` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`user_account` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
  `surname` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
  `email` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `role_id` INT(10) UNSIGNED NOT NULL,
  `password` VARCHAR(100) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_user_account_role1_idx` (`role_id` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE,
  CONSTRAINT `fk_user_account_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `student_testing_db`.`role` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `student_testing_db`.`stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`stats` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`stats` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_account_id` INT(10) UNSIGNED NOT NULL,
  `test_id` INT(10) UNSIGNED NOT NULL,
  `score` INT(11) NULL DEFAULT NULL,
  `time_passed` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_stats_user_account_idx` (`user_account_id` ASC) INVISIBLE,
  INDEX `fk_stats_test1_idx` (`test_id` ASC) VISIBLE,
  CONSTRAINT `fk_stats_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `student_testing_db`.`test` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_stats_user_account`
    FOREIGN KEY (`user_account_id`)
    REFERENCES `student_testing_db`.`user_account` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `student_testing_db`.`task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`task` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`task` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `test_id` INT(10) UNSIGNED NOT NULL,
  `question` VARCHAR(350) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NULL DEFAULT NULL,
  `correctAnswer` VARCHAR(250) CHARACTER SET 'utf8' COLLATE 'utf8_general_ci' NOT NULL,
  `image_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_task_test1_idx` (`test_id` ASC) VISIBLE,
  INDEX `fk_task_image1_idx` (`image_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_image1`
    FOREIGN KEY (`image_id`)
    REFERENCES `student_testing_db`.`image` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `fk_task_test1`
    FOREIGN KEY (`test_id`)
    REFERENCES `student_testing_db`.`test` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `student_testing_db`.`task_correct_answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`task_correct_answer` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`task_correct_answer` (
  `task_id` INT(10) UNSIGNED NOT NULL,
  `answer_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`task_id`, `answer_id`),
  INDEX `fk_task_has_answer_answer1_idx` (`answer_id` ASC) VISIBLE,
  INDEX `fk_task_has_answer_task1_idx` (`task_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_correct_answer_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `student_testing_db`.`task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_task_correct_answer_answer1`
    FOREIGN KEY (`answer_id`)
    REFERENCES `mydb`.`correctAnswer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `student_testing_db`.`task_possible_answer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `student_testing_db`.`task_possible_answer` ;

CREATE TABLE IF NOT EXISTS `student_testing_db`.`task_possible_answer` (
  `task_id` INT(10) UNSIGNED NOT NULL,
  `answer_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`task_id`, `answer_id`),
  INDEX `fk_task_has_answer_answer1_idx` (`answer_id` ASC) VISIBLE,
  INDEX `fk_task_has_answer_task1_idx` (`task_id` ASC) VISIBLE,
  CONSTRAINT `fk_task_possible_answer_task1`
    FOREIGN KEY (`task_id`)
    REFERENCES `student_testing_db`.`task` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_task_possible_answer_answer1`
    FOREIGN KEY (`answer_id`)
    REFERENCES `mydb`.`correctAnswer` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
