INSERT INTO AUTHORS(NAME) VALUES ('Ivan Petrov');
INSERT INTO GENRES(NAME) VALUES ('Comedy');
INSERT INTO BOOKS(NAME, AUTHOR_ID, GENRE_ID) VALUES ('Russian modern history', CAST(4 AS BIGINT), CAST(5 AS BIGINT));