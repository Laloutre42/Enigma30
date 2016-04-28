ALTER TABLE `enigme30`.`enigma` 
ADD COLUMN `indice` VARCHAR(255) NULL DEFAULT NULL AFTER `answer`;

UPDATE `enigme30`.`enigma` SET `indice`='Celle-ci tu dois pouvoir la trouver :)' WHERE `id`='1';
UPDATE `enigme30`.`enigma` SET `indice`='Vraiment utile ces indices' WHERE `id`='2';
UPDATE `enigme30`.`enigma` SET `indice`='Un village' WHERE `id`='4';
UPDATE `enigme30`.`enigma` SET `indice`='C\'est un hameau mais il y a une commune qui porte ce nom dans la Loire (8 lettres)' WHERE `id`='5';
UPDATE `enigme30`.`enigma` SET `indice`='Courage' WHERE `id`='6';
UPDATE `enigme30`.`enigma` SET `indice`='Sur la carte c\'est un petit carré vert, non loin d\'une Madone' WHERE `id`='3';

