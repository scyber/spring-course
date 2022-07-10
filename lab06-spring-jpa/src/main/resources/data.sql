INSERT INTO AUTHORS(NAME) VALUES ('Ernest Hemingway');
INSERT INTO AUTHORS(NAME) VALUES ('Jules Verne');
INSERT INTO AUTHORS(NAME) VALUES ('James Hadley Chase');
INSERT INTO GENRES(NAME) VALUES ('Drama');
INSERT INTO GENRES(NAME) VALUES ('Fantasy');
INSERT INTO GENRES(NAME) VALUES ('Romance');
INSERT INTO GENRES(NAME) VALUES ('Thriller');
INSERT INTO GENRES(NAME) VALUES ('Crime');
INSERT INTO BOOKS(NAME, AUTHOR_ID, GENRE_ID) VALUES ('The Old Man and The Sea', CAST(1 AS BIGINT), CAST(1 AS BIGINT));
INSERT INTO BOOKS(NAME, AUTHOR_ID, GENRE_ID) VALUES ('The Sun Also Rises',CAST(1 AS BIGINT) , CAST(2 AS BIGINT));
INSERT INTO BOOKS(NAME, AUTHOR_ID, GENRE_ID) VALUES ('Journey to the Centre of the Earth',CAST(2 AS BIGINT),CAST(2 AS BIGINT));
INSERT INTO BOOKS(NAME, AUTHOR_ID, GENRE_ID) VALUES ('Twenty Thousand Leagues Under the Sea',CAST(2 AS BIGINT) ,CAST(2 AS BIGINT));
INSERT INTO BOOKS(NAME, AUTHOR_ID, GENRE_ID) VALUES ('Around the World in 80 Days',CAST(2 AS BIGINT) ,CAST(2 AS BIGINT));
INSERT INTO BOOKS(NAME, AUTHOR_ID, GENRE_ID) VALUES ('The World In My Pocket',CAST(3 AS BIGINT) ,CAST(5 AS BIGINT));
INSERT INTO BOOKS(NAME, AUTHOR_ID, GENRE_ID) VALUES ('Just the Way It Is',CAST(3 AS BIGINT) ,CAST(5 AS BIGINT));

