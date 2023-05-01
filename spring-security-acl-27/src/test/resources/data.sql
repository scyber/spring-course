INSERT INTO AUTHORS(NAME) VALUES ('Ivan Petrov');
INSERT INTO GENRES(NAME) VALUES ('Comedy');
INSERT INTO BOOKS(TITLE) VALUES ('Russian modern history');
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(1 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(1 AS BIGINT), CAST(1 AS BIGINT));



INSERT INTO users(user_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES ('testuser', '$2y$10$xKJ8w67HFKdv/5R3gtChS.A7DT.CmzXqF/rv1tdC.zmjhQURiIM8i', 1, 1, 1, 1);
