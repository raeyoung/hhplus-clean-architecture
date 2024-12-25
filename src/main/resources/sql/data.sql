
-- -- Lecture
INSERT INTO lecture (id, title, instructor_name, capacity, startAt, end_at, lecture_status) VALUES (1, 'lecturer1', '하헌우', 0, '2024-12-01', '2024-12-31','OPENED');
INSERT INTO lecture (id, title, instructor_name, capacity, startAt, end_at, lecture_status) VALUES (2, 'lecturer1', '하헌우', 0, '2024-12-01', '2024-12-31','OPENED');
INSERT INTO lecture (id, title, instructor_name, capacity, startAt, end_at, lecture_status) VALUES (3, 'lecturer2', '김래영', 30, '2024-12-01', '2024-12-31','CLOSED');
INSERT INTO lecture (id, title, instructor_name, capacity, startAt, end_at, lecture_status) VALUES (4, 'lecturer3', '김래영', 0, '2024-12-01', '2024-12-31','CLOSED');
INSERT INTO lecture (id, title, instructor_name, capacity, startAt, end_at, lecture_status) VALUES (5, 'lecturer3', '김영래', 0, '2024-12-01', '2024-12-31','CLOSED');

-- Lecture_application
INSERT INTO lecture_application (user_id, lecture_id, applied_at, status)
VALUES (9997, 5, '2024-09-29 12:00:00', 'SUCCESS');

-- Users
INSERT INTO users (id, email, name, password) VALUES (1, 'user1', '김영래1', 'password');
INSERT INTO users (id, email, name, password) VALUES (2, 'user1', '김영래2', 'password');
INSERT INTO users (id, email, name, password) VALUES (3, 'user1', '김영래3', 'password');
INSERT INTO users (id, email, name, password) VALUES (4, 'user1', '김영래4', 'password');
INSERT INTO users (id, email, name, password) VALUES (5, 'user1', '김영래5', 'password');
INSERT INTO users (id, email, name, password) VALUES (6, 'user1', '김영래6', 'password');
INSERT INTO users (id, email, name, password) VALUES (7, 'user1', '김영래7', 'password');
INSERT INTO users (id, email, name, password) VALUES (8, 'user1', '김영래8', 'password');
INSERT INTO users (id, email, name, password) VALUES (9, 'user1', '김영래9', 'password');
INSERT INTO users (id, email, name, password) VALUES (10, 'user1', '김영래10', 'password');
INSERT INTO users (id, email, name, password) VALUES (11, 'user1', '김영래11', 'password');
INSERT INTO users (id, email, name, password) VALUES (12, 'user1', '김영래12', 'password');
INSERT INTO users (id, email, name, password) VALUES (13, 'user1', '김영래13', 'password');
INSERT INTO users (id, email, name, password) VALUES (14, 'user1', '김영래14', 'password');
INSERT INTO users (id, email, name, password) VALUES (15, 'user1', '김영래15', 'password');
INSERT INTO users (id, email, name, password) VALUES (16, 'user1', '김영래16', 'password');
INSERT INTO users (id, email, name, password) VALUES (17, 'user1', '김영래17', 'password');
INSERT INTO users (id, email, name, password) VALUES (18, 'user1', '김영래18', 'password');
INSERT INTO users (id, email, name, password) VALUES (19, 'user1', '김영래19', 'password');
INSERT INTO users (id, email, name, password) VALUES (20, 'user1', '김영래20', 'password');



