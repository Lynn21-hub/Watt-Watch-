CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(100) NOT NULL,
    `surname` VARCHAR(100),
    `email` VARCHAR(255) NOT NULL,
    `address` TEXT,
    `alerting` TINYINT(1) NOT NULL DEFAULT 0,
    `alertThreshold` DOUBLE NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`),
    UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
