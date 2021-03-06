-- MySQL Script generated by MySQL Workbench
-- Wed May 20 15:55:52 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema mycareer
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `birth` DATE NOT NULL,
  `gender` CHAR NOT NULL,
  `job` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(11) NOT NULL,
  `provider` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`url`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`url` (
  `id` INT NOT NULL,
  `url` TEXT NULL,
  `url_info` VARCHAR(100) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_url_user_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_url_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`qualification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`qualification` (
  `id` INT NOT NULL,
  `q_title` VARCHAR(100) NOT NULL,
  `gain_day` DATE NOT NULL,
  `grade` CHAR NULL,
  `score` INT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_qualification_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_qualification_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`award`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`award` (
  `id` INT NOT NULL,
  `a_title` VARCHAR(45) NULL,
  `a_info` VARCHAR(45) NULL,
  `gain_day` VARCHAR(45) NULL,
  `grade` VARCHAR(45) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_award_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_award_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`language`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`language` (
  `id` INT NOT NULL,
  `language` VARCHAR(45) NULL,
  `level` CHAR NULL,
  `reason` VARCHAR(100) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_language_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_language_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`career`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`career` (
  `id` INT NOT NULL,
  `c_title` VARCHAR(100) NOT NULL,
  `start` DATE NOT NULL,
  `end` DATE NULL,
  `detail` VARCHAR(100) NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_career_user1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_career_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`project` (
  `id` INT NOT NULL,
  `project_title` VARCHAR(100) NOT NULL,
  `project_info` TEXT NULL,
  `contribution` INT NULL,
  `url` TEXT NULL,
  `start_day` DATE NOT NULL,
  `end_day` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `id` INT NOT NULL,
  `role_name` VARCHAR(45) NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`id`, `project_id`),
  INDEX `fk_role_project1_idx` (`project_id` ASC) VISIBLE,
  CONSTRAINT `fk_role_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `mydb`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`role_develop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role_develop` (
  `id` INT NOT NULL,
  `develop_name` VARCHAR(100) NULL,
  `develop_info` TEXT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`, `role_id`),
  INDEX `fk_role_develop_role1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_role_develop_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`api`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`api` (
  `id` INT NOT NULL,
  `api_name` VARCHAR(100) NULL,
  `api_info` TEXT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`id`, `project_id`),
  INDEX `fk_api_project1_idx` (`project_id` ASC) VISIBLE,
  CONSTRAINT `fk_api_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `mydb`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`project_img`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`project_img` (
  `id` INT NOT NULL,
  `src` TEXT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_img_project1_idx` (`project_id` ASC) VISIBLE,
  CONSTRAINT `fk_project_img_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `mydb`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tech`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tech` (
  `id` INT NOT NULL,
  `tech_name` VARCHAR(45) NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tech_project1_idx` (`project_id` ASC) VISIBLE,
  CONSTRAINT `fk_tech_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `mydb`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
