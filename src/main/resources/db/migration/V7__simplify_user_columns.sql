ALTER TABLE `users` DROP CONSTRAINT `uk_users_username`;

ALTER TABLE `users`
    DROP COLUMN `first_name`,
    DROP COLUMN `last_name`,
    DROP COLUMN `phone`;

ALTER TABLE `users` CHANGE COLUMN `username` `name` varchar(100) NOT NULL;
