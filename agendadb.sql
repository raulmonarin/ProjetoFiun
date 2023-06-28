-- MySQL Script generated by MySQL Workbench
-- Sat Jan 26 19:15:49 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema agendadb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `agendadb` ;

-- -----------------------------------------------------
-- Schema agendadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `agendadb` DEFAULT CHARACTER SET utf8 ;
USE `agendadb` ;

-- -----------------------------------------------------
-- Table `agendadb`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendadb`.`usuario` ;

CREATE TABLE IF NOT EXISTS `agendadb`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(20) UNIQUE NOT NULL,
  `senha` VARCHAR(60) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `agendadb`.`contato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `agendadb`.`contato` ;

CREATE TABLE IF NOT EXISTS `agendadb`.`contato` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(50) NULL,
  `email` VARCHAR(70) NULL,
  `telefone` VARCHAR(13) NULL,
  `usuario_id` INT NOT NULL,
  PRIMARY KEY (`id`, `usuario_id`),
  INDEX `fk_contato_usuario_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_contato_usuario`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `agendadb`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
