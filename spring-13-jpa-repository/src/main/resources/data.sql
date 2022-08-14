INSERT INTO AUTHORS(NAME) VALUES ('Ernest Hemingway');
INSERT INTO AUTHORS(NAME) VALUES ('Jules Verne');
INSERT INTO AUTHORS(NAME) VALUES ('James Hadley Chase');
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

INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(1 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(2 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(3 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(4 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(5 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(6 AS BIGINT), CAST(3 AS BIGINT));
INSERT INTO AUTHORS_BOOKS(BOOK_ID, AUTHOR_ID) VALUES (CAST(7 AS BIGINT), CAST(3 AS BIGINT));

INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(1 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(2 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(3 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(4 AS BIGINT), CAST(3 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(5 AS BIGINT), CAST(2 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(6 AS BIGINT), CAST(5 AS BIGINT));
INSERT INTO GENRES_BOOKS(BOOK_ID, GENRE_ID) VALUES(CAST(7 AS BIGINT), CAST(5 AS BIGINT));