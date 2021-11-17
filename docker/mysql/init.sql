CREATE DATABASE IF NOT EXISTS `house_bills_api` CHARACTER SET utf8 COLLATE utf8_unicode_ci;
CREATE USER IF NOT EXISTS 'house_bills_user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `house_bills_api`.* TO 'house_bills_user'@'%';
