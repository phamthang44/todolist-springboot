USE todolist_app_2;

CREATE TABLE `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

INSERT INTO users (`username`, `email`, `password`) VALUES ('test.1', 'test@gmail.com', '1233456');
INSERT INTO users (`username`, `email`, `password`) VALUES ('test.2', 'test2@gmail.com', '1233456');
INSERT INTO users (`username`, `email`, `password`) VALUES ('test.3', 'test3@gmail.com', '1233456');
CREATE TABLE `todolists` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);


INSERT INTO todolists (`user_id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'Test.1 Work Tasks', '2025-04-28 09:00:00', '2025-04-28 09:00:00'),
(1, 'Test.1 Shopping List', '2025-04-28 10:00:00', '2025-04-28 10:00:00'),
(2, 'Test.2 Project Plan', '2025-04-28 11:00:00', '2025-04-28 11:00:00'),
(2, 'Test.2 Travel Checklist', '2025-04-28 12:00:00', '2025-04-28 12:00:00'),
(3, 'Test.3 Study Goals', '2025-04-28 13:00:00', '2025-04-28 13:00:00'),
(3, 'Test.3 Fitness Plan', '2025-04-28 14:00:00', '2025-04-28 14:00:00');

CREATE TABLE `tasks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `todolist_id` INT NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT,
  `status` ENUM('PENDING', 'IN_PROGRESS', 'DONE') DEFAULT 'PENDING',
  `priority` ENUM('LOW', 'MEDIUM', 'HIGH') DEFAULT 'MEDIUM',
  `due_date` DATETIME DEFAULT NULL,
  `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `todolist_id` (`todolist_id`),
  CONSTRAINT `tasks_ibfk_2` FOREIGN KEY (`todolist_id`) REFERENCES `todolists` (`id`) ON DELETE CASCADE
);

INSERT INTO tasks (`todolist_id`, `title`, `description`, `status`, `priority`, `due_date`, `created_at`, `updated_at`) VALUES
(1, 'Finish report', 'Complete the quarterly report for Q1', 'IN_PROGRESS', 'HIGH', '2025-04-30 17:00:00', '2025-04-28 09:15:00', '2025-04-28 09:15:00'),
(2, 'Buy groceries', 'Get milk, eggs, and bread', 'PENDING', 'MEDIUM', '2025-04-29 12:00:00', '2025-04-28 10:15:00', '2025-04-28 10:15:00'),
(3, 'Schedule meeting', 'Set up a team meeting for next week', 'DONE', 'LOW', NULL, '2025-04-28 11:15:00', '2025-04-28 11:15:00'),
(1, 'Book flights', 'Book flights for the conference', 'PENDING', 'HIGH', '2025-05-01 09:00:00', '2025-04-28 12:15:00', '2025-04-28 12:15:00'),
(2, 'Read chapter 5', 'Read chapter 5 for the study group', 'IN_PROGRESS', 'MEDIUM', '2025-04-30 15:00:00', '2025-04-28 13:15:00', '2025-04-28 13:15:00');

SELECT * FROM tasks;
SELECT * FROM users;
SELECT * FROM todolists;

SHOW CREATE TABLE todolists;
SHOW CREATE TABLE tasks;

ALTER TABLE tasks DROP COLUMN user_id;
