-- Create database schema.
CREATE SCHEMA `cookbook_group3` ;

-- Create tables.
CREATE TABLE `cookbook_group3`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `status` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB;

-- Insert informations into the table.
INSERT INTO `cookbook_group3`.`user` (`username`, `password`, `status`) VALUES ('admin', '8106417f482b5b3a30a433f4a31704bf', '1');
INSERT INTO `cookbook_group3`.`user` (`username`, `password`, `status`) VALUES ('eason', '8106417f482b5b3a30a433f4a31704bf', '1');
INSERT INTO `cookbook_group3`.`user` (`username`, `password`, `status`) VALUES ('jungang', '8106417f482b5b3a30a433f4a31704bf', '0');
INSERT INTO `cookbook_group3`.`user` (`username`, `password`, `status`) VALUES ('jiaxiang', '8106417f482b5b3a30a433f4a31704bf', '1');
INSERT INTO `cookbook_group3`.`user` (`username`, `password`, `status`) VALUES ('kongyu', '8106417f482b5b3a30a433f4a31704bf', '1');

CREATE TABLE `cookbook_group3`.`recipe` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ownerUserid` INT NULL,
  `recipeName` VARCHAR(50) NULL,
  `description` VARCHAR(500) NULL,
  `preparationTime` INT NULL,
  `cookingTime` INT NULL,
  `peopleAvailable` INT NULL,
  `imagePath` VARCHAR(50) NULL,
  `status` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
COMMENT = 'recipe table in the digital cookbook';

-- Insert informations into the table.
INSERT INTO `cookbook_group3`.`recipe` (`ownerUserid`, `recipeName`, `description`, `preparationTime`, `cookingTime`, `peopleAvailable`, `imagePath`, `status`) VALUES ('1', 'Gong Bao Jiding', 'Sichuan Dish', '30', '10', '4', '/src/gbjd.png', '1');
INSERT INTO `cookbook_group3`.`recipe` (`ownerUserid`, `recipeName`, `description`, `preparationTime`, `cookingTime`, `peopleAvailable`, `imagePath`, `status`) VALUES ('3', 'Suan La Fen', 'Sichuan Dish', '5', '100', '2', '/src/slf.jpg', '1');
INSERT INTO `cookbook_group3`.`recipe` (`ownerUserid`, `recipeName`, `description`, `preparationTime`, `cookingTime`, `peopleAvailable`, `imagePath`, `status`) VALUES ('4', 'Hong Shao Rou', 'Hunan Dish', '30', '5', '4', '/src/hsr.png', '0');


CREATE TABLE `cookbook_group3`.`ingredient` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ingredientName` VARCHAR(30) NULL,
  `recipeID` INT NULL,
  `quantity` DECIMAL(2) NULL,
  `unit` VARCHAR(10) NULL,
  `comments` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
COMMENT = 'ingredient table for digital cookbook';

-- Insert informations into the table.
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('cornstarch', '1', '1.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('soy sauce', '1', '4.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('chicken breast', '1', '0.5', 'kg');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('Shaoxin rice wine', '1', '3.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('sugar', '1', '2.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('chicken stock', '1', '3.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('Chiangang vinegar', '1', '4.0', 'teaspoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('sesame oil', '1', '4.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('dark soy sauce', '1', '2.0', 'teaspoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('peanut oil', '1', '3.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('dried red chilis', '1', '12.0', 'pieces', 'stemmed, halved crosswise, and seeded');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('scallions', '1', '5.0', 'pieces', 'white part onyl, thickly sliced crosswise');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('garlic', '1', '1.0', 'cloves', 'peeled, thinly sliced');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('ginger', '1', '0.5', 'pieces', 'peeled, minced');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('peanuts', '1', '0.5', 'cups', 'peeled, thinly sliced');

INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('pork belly', '2', '0.5', 'kg', 'cut into 2cm pieces');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('cooking oil', '2', '2.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('brown sugar', '2', '1.0', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('Shaoxin rice wine', '2', '3', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('light soy sauce', '2', '1', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('dark soy sauce', '2', '0.5', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('chicken stock or water', '2', '2', 'cups');

INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('potato noodles', '3', '1', 'bunch');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('peanuts', '3', '2', 'tablespoon', 'roasted');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('spring onion', '3', '1', 'tablespoon', 'chopped');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('coriander', '3', '1', 'tablespoon', 'chopped');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`, `comments`) VALUES ('pickled Sichuan vegetable', '3', '2', 'tablespoon', 'chopped');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('garlic', '3', '3', 'cloves');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('peanut oil', '3', '0.5', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('Sichuan peppercorn powder', '3', '0.5', 'teaspoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('Chinese five spicy powder', '3', '0.5', 'teaspoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('chili powder', '3', '1', 'teaspoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('vinegar', '3', '1', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('light soy sauce', '3', '1', 'tablespoon');
INSERT INTO `cookbook_group3`.`ingredient` (`ingredientName`, `recipeID`, `quantity`, `unit`) VALUES ('salt', '3', '1', 'teaspoon');

CREATE TABLE `cookbook_group3`.`step` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(500) NULL,
  `stepOrder` INT NULL,
  `recipeID` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
COMMENT = 'step table for digital cookbook.';

-- Insert informations into the table.
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Mix together cornstarch and 1 tbsp. of the soy sauce in a medium bowl.', '1', '1');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Add chicken, toss well, and set aside to marinate for 30 minutes.', '2', '1');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Meanwhile, mix together the remaining 3 tbsp. soy sauce, rice wine, sugar, stock, vinegar, sesame oil, and dark soy sauce.', '3', '1');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Set aside.', '4', '1');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Heat peanut oil in a wok or large nonstick skillet over high heat until just beginning to smoke.', '5', '1');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Add chilis, half the scallions, garlic, ginger, and chicken and stir-fry until chicken is golden, 3-5 minutes.', '6', '1');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Add soy sauce mixture and stir-fry until sauce thickens, about 2 minutes.', '7', '1');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Stir in peanuts.', '8', '1');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Garnish with remaining scallions.', '9', '1');

INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Bring a pot of water to a boil and blanch the pork for a couple minutes.', '1', '2');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Take the pork out of the pot and set aside.', '2', '2');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Over low heat, add oil and sugar to your wok.', '3', '2');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Melt the sugar slightly and add the pork.', '4', '2');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Raise the heat to medium and cook until the pork is lightly browned.', '5', '2');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Turn the heat back down to low and add cooking wine, light soy sauce, dark soy sauce, and chicken stock.', '6', '2');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Cover and simmer for about 60 minutes to 90 minutes until pork is fork tender.', '7', '2');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Every 5-10 minutes, stir to prevent burning and add water if it gets too dry.', '8', '2');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Once the pork is fork tender, if there is still a lot of visible liquid, uncover the wok, turn up the heat, and stir continuously the sauce has reduced to a glistening coating.', '9', '2');

INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Soak the sweet potato noodles with hot water around 30 minutes.', '1', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Transfer out and set aside.', '2', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('In the serving bowls and mix all the seasonings.', '3', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Heat up peanuts oil in pan to stir-fry the mashed garlic until aroma.', '4', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Mix the garlic oil with the seasonings.', '5', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Add some spring onions and corianders in serving bowls.', '6', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Pour in boiling water or stock to tune the seasonings.', '7', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Add vinegar and light soy sauce.', '8', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Mix well and set aside.', '9', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Cook soaked sweet potato noodles around 3~5 minutes until you can break the noodles with your fingers.', '10', '3');
INSERT INTO `cookbook_group3`.`step` (`content`, `stepOrder`, `recipeID`) VALUES ('Transfer the noodles out to the serving bowls and then add top with pickled vegetables, roasted peanuts and chopped spring onions and coriander.', '11', '3');

CREATE TABLE `cookbook_group3`.`user-recipe-table` (
  `userID` INT NULL,
  `recipeID` INT NULL)
COMMENT = 'to store user\'s favorite recipes';

-- Insert informations into the table.











