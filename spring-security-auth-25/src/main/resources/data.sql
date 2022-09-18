INSERT INTO AUTHORS(NAME) VALUES ('Ernest Hemingway');
INSERT INTO AUTHORS(NAME) VALUES ('Jules Verne');
INSERT INTO AUTHORS(NAME) VALUES ('James Hadley Chase');
INSERT INTO AUTHORS(NAME) VALUES ('SAMPLE AUTHOR');
INSERT INTO GENRES(NAME) VALUES ('Drama');
INSERT INTO GENRES(NAME) VALUES ('Fantasy');
INSERT INTO GENRES(NAME) VALUES ('Romance');
INSERT INTO GENRES(NAME) VALUES ('Thriller');
INSERT INTO GENRES(NAME) VALUES ('Crime');

INSERT INTO BOOKS(TITLE) VALUES ('The Old Man and The Sea');
INSERT INTO BOOKS(TITLE) VALUES ('The Sun Also Rises');
INSERT INTO BOOKS(TITLE) VALUES ('Journey to the Centre of the Earth');
INSERT INTO BOOKS(TITLE) VALUES ('Twenty Thousand Leagues Under the Sea');
INSERT INTO BOOKS(TITLE) VALUES ('Around the World in 80 Days');
INSERT INTO BOOKS(TITLE) VALUES ('The World In My Pocket');
INSERT INTO BOOKS(TITLE) VALUES ('Just the Way It Is');
INSERT INTO BOOKS(TITLE) VALUES ('Sample Book with Multi Authors and Genres');

INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(1 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(2 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(3 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(4 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(5 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(6 AS BIGINT), CAST(3 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(7 AS BIGINT), CAST(3 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(8 AS BIGINT), CAST(4 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(8 AS BIGINT), CAST(3 AS BIGINT));

INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(1 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(2 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(3 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(4 AS BIGINT), CAST(3 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(5 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(6 AS BIGINT), CAST(5 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(7 AS BIGINT), CAST(5 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(8 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(8 AS BIGINT), CAST(2 AS BIGINT));

INSERT INTO COMMENTS(BOOK_ID, TITLE) VALUES(CAST (1 AS BIGINT), 'Test Comment');
INSERT INTO COMMENTS(BOOK_ID, TITLE) VALUES(CAST( 1 AS BIGINT ), 'Next Comment');

INSERT INTO users(user_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
('admin', '$2y$10$4cyMqF3jykO0CasWiUOIdOaymtshZ7sHrBfBYKG7V/xBM2AW/S1v6', 1, 1, 1, 1),
('user', '$2y$10$xKJ8w67HFKdv/5R3gtChS.A7DT.CmzXqF/rv1tdC.zmjhQURiIM8i', 1, 1, 1, 1);

INSERT INTO authorities (user_id, authority) VALUES
(CAST( 1 AS BIGINT), 'ROLE_ADMIN'),
(CAST( 1 AS BIGINT), 'ROLE_USER'),
(CAST( 2 AS BIGINT), 'ROLE_USER');
