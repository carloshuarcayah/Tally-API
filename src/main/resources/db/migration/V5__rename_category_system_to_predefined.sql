ALTER TABLE `categories`
    CHANGE COLUMN `system` `predefined` BIT(1) NOT NULL DEFAULT b'0';
