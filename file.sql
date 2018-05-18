CREATE SCHEMA `cookbook_group3` ;

CREATE TABLE `cookbook_group3`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB;

CREATE TABLE `cookbook_group3`.`recipe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ownerUserid` INT NULL,
  `recipeName` VARCHAR(30) NULL,
  `description` VARCHAR(500) NULL,
  `preparationTime` INT NULL,
  `cookingTime` INT NULL,
  `imagePath` VARCHAR(40) NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
COMMENT = 'recipe table in the digital cookbook';

CREATE TABLE `cookbook_group3`.`ingredient` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ingredientName` VARCHAR(30) NULL,
  `recipeID` INT NULL,
  `quantity` DECIMAL(2) NULL,
  `unit` VARCHAR(5) NULL,
  PRIMARY KEY (`id`))
COMMENT = 'ingredient table for digital cookbook';

CREATE TABLE `cookbook_group3`.`step` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(100) NULL,
  `stepOrder` INT NULL,
  `recipeID` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
COMMENT = 'step table for digital cookbook.';

CREATE TABLE `cookbook_group3`.`user-recipe-table` (
  `userID` INT NULL,
  `recipeID` INT NULL)
COMMENT = 'to store user\'s favorite recipes';


