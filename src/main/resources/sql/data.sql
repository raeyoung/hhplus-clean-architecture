
-- -- Lecture
INSERT INTO lecture (id, title, instructor_name, capacity, start_at, end_at, lecture_status) VALUES (1, 'Java', '하헌우', 0, '2024-12-01', '2024-12-31','OPENED');
INSERT INTO lecture (id, title, instructor_name, capacity, start_at, end_at, lecture_status) VALUES (2, 'Spring', '하헌우', 0, '2024-12-01', '2024-12-31','OPENED');
INSERT INTO lecture (id, title, instructor_name, capacity, start_at, end_at, lecture_status) VALUES (3, 'AI', '김래영', 30, '2024-12-01', '2024-12-31','CLOSED');
INSERT INTO lecture (id, title, instructor_name, capacity, start_at, end_at, lecture_status) VALUES (4, 'React', '김래영', 0, '2024-12-01', '2024-12-31','CLOSED');
INSERT INTO lecture (id, title, instructor_name, capacity, start_at, end_at, lecture_status) VALUES (5, 'Machine Learning', '김영래', 0, '2024-12-01', '2024-12-31','CLOSED');

-- Lecture_application
INSERT INTO lecture_application (user_id, lecture_id, applied_at, application_status)
VALUES (1, 5, '2024-09-29 12:00:00', 'SUCCESS');

-- Users
INSERT INTO users (id, user_id, name, password) VALUES (1, 'user1', '김영래1', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (2, 'user2', '김영래2', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (3, 'user3', '김영래3', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (4, 'user4', '김영래4', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (5, 'user5', '김영래5', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (6, 'user6', '김영래6', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (7, 'user7', '김영래7', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (8, 'user8', '김영래8', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (9, 'user9', '김영래9', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (10, 'user10', '김영래10', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (11, 'user11', '김영래11', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (12, 'user12', '김영래12', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (13, 'user13', '김영래13', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (14, 'user14', '김영래14', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (15, 'user15', '김영래15', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (16, 'user16', '김영래16', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (17, 'user17', '김영래17', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (18, 'user18', '김영래18', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (19, 'user19', '김영래19', 'password');
INSERT INTO users (id, user_id, name, password) VALUES (20, 'user20', '김영래20', 'password');



