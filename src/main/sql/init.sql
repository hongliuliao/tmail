CREATE TABLE `tmail_users` (
`id`  int NOT NULL AUTO_INCREMENT ,
`name`  varchar(50) NOT NULL ,
`password` varchar(50) NULL ,
PRIMARY KEY (`id`)
);

CREATE TABLE `tmail_accounts` (
`id`  int NOT NULL AUTO_INCREMENT ,
`email`  varchar(50) NOT NULL ,
`email_password`  varchar(50) NOT NULL ,
`user_id`  int NOT NULL ,
`last_message_num`  int NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `idx_email` (`email`)
);
