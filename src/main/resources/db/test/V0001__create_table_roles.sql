-- template.roles_seq definition

CREATE TABLE IF NOT EXISTS `roles_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO roles_seq (next_val) VALUES (0);

CREATE TABLE roles (
	id BIGINT auto_increment NOT NULL,
	name varchar(100) NOT NULL,
	value varchar(100) NOT NULL,
	status bool NULL DEFAULT true,
	created_by varchar(250) NOT NULL,
	created_date timestamp NOT NULL,
	updated_by varchar(255) NOT NULL,
	updated_date timestamp NOT NULL,
	CONSTRAINT role_PK PRIMARY KEY (id),
	CONSTRAINT role_UN UNIQUE KEY (name,value)
);

INSERT INTO roles (name, value, status, created_by, created_date, updated_by, updated_date)
VALUES ('ROLE_ADMIN', 'ADMIN', 1, 'ADMIN', NOW(), 'ADMIN', NOW()),
('ROLE_CUSTOMER', 'Cliente', 1, 'ADMIN', NOW(), 'ADMIN', NOW());

-- template.users_seq definition

CREATE TABLE IF NOT EXISTS `users_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users_seq (next_val) VALUES (0);

-- template.users definition

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `create_user` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `last_modified_user` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  `cellphone` varchar(255) NOT NULL,
  `email_address` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `genre` tinyint DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(200) NOT NULL,
  `username` varchar(20) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK_sx468g52bpetvlad2j9y0lptc` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO users
(id, create_user, create_date, last_modified_user, last_modified_date, cellphone, email_address, first_name, genre, last_name, password, username, status)
VALUES(1, 'ADMIN', '2022-12-08 02:22:01', 'ADMIN', '2022-12-08 02:22:01', '6671568899', 'felipemonzon2705@gmail.com', 'Felipe', 0, 'Monzon', '$2a$10$K9UyV7Eiwoi8Udv/9R5kROuDvz/K6ZVLJzzESW2lVe7B.FfXRg0hK', 'felipemonzon2705', 'ACTIVE');

INSERT INTO users
(id, create_user, create_date, last_modified_user, last_modified_date, cellphone, email_address, first_name, genre, last_name, password, username, status)
VALUES(2, 'ADMIN', '2022-12-08 02:22:01', 'ADMIN', '2022-12-08 02:22:01', '6671568899', 'felipemonzon2705@gmail.com', 'Felipe', 0, 'Monzon', '$2a$10$K9UyV7Eiwoi8Udv/9R5kROuDvz/K6ZVLJzzESW2lVe7B.FfXRg0hK', 'felipemonzon', 'PENDING_TO_CONFIRM');

-- template.user_roles definition

CREATE TABLE `user_roles` (
  `id_user` bigint NOT NULL,
  `id_role` bigint NOT NULL,
  PRIMARY KEY (`id_user`,`id_role`),
  KEY `FK1v995xldvmr6w96c5feofx1gf` (`id_role`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user_roles(id_user, id_role)
VALUES(1, 1);

-- template.confirmation_tokens_seq definition

CREATE TABLE IF NOT EXISTS `confirmation_tokens_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO confirmation_tokens_seq (next_val) VALUES (0);

-- template.confirmation_tokens definition

CREATE TABLE `confirmation_tokens` (
  `token_id` bigint NOT NULL,
  `confirmation_token` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `expiration_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`token_id`),
  KEY `FKhpuw37a1pqxfb6ya1nv5lm4ga` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO confirmation_tokens
(token_id, confirmation_token, created_date, user_id, expiration_date, status)
VALUES(2, '7b920dac-d5d2-4e6e-91f7-6c2f848e366d', '2022-12-11 00:55:51', 1, DATE_ADD(NOW(), INTERVAL 7 DAY), 'ACTIVE');

INSERT INTO confirmation_tokens
(token_id, confirmation_token, created_date, user_id, expiration_date, status)
VALUES(3, 'e8487113-d915-4c4e-9a40-dc85e44d19e2', '2022-12-11 00:55:51', 1, '2022-12-11 11:50:21', 'ACTIVE');

INSERT INTO confirmation_tokens
(token_id, confirmation_token, created_date, user_id, expiration_date, status)
VALUES(4, 'a7749c83-c1ed-4bf7-9f8f-a50105a526c5', '2022-12-11 00:55:51', 1, '2022-12-11 11:50:21', 'ACTIVE');
