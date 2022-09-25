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
(CAST( 2 AS BIGINT), 'ROLE_USER'),
(CAST( 1 AS BIGINT), 'ROLE_EDITOR');                                 ;

INSERT INTO acl_sid (id, principal, sid) VALUES
--  (1, 1, 'admin'),
--  (2, 1, 'user'),
 (1, 0, 'ROLE_ADMIN'),
 (2, 0, 'ROLE_USER'),
 (3, 0, 'ROLE_EDITOR');

INSERT INTO acl_class (id, class) VALUES
(CAST (1 AS BIGINT), 'ru.otus.domain.Book'),
(CAST (2 AS BIGINT), 'ru.otus.domain.Author'),
(CAST (3 AS BIGINT), 'ru.otus.domain.Genre'),
(CAST (4 AS BIGINT), 'ru.otus.domain.Comment');


INSERT INTO acl_object_identity (id, object_id_class /*acl_class(id) */, object_id_identity, parent_object, owner_sid /* acl_sid(id)*/, entries_inheriting) VALUES
(1, 1, 1, NULL, 1, 0),
(2, 1, 2, NULL, 1, 0),
(3, 1, 3, NULL, 1, 0),
(4, 1, 4, NULL, 1, 0),
(5, 1, 5, NULL, 1, 0),
(6, 1, 6, NULL, 1, 0),
(7, 1, 7, NULL, 1, 0),
(8, 1, 8, NULL, 1, 0),
(9, 2, 1, NULL, 1, 0),
(10, 2, 2, NULL, 1, 0),
(11, 2, 3, NULL, 1, 0),
(12, 2, 4, NULL, 1, 0),
(13, 3, 1, NULL, 1, 0),
(14, 3, 2, NULL, 1, 0),
(15, 3, 3, NULL, 1, 0),
(16, 3, 4, NULL, 1, 0),
(17, 3, 5, NULL, 1, 0),
(18, 4 , 1, NULL, 1, 0),
(19, 4 , 2, NULL, 1, 0);

INSERT INTO acl_entry ( acl_object_identity /*acl_object_identity(id) */, ace_order, sid /*ROLE_ID or USER_ID*/, mask, granting, audit_success, audit_failure) VALUES
 ( 1, 1, 1, 1, 1, 1, 1),
 ( 2, 1, 1, 1, 1, 1, 1),
 ( 3, 1, 1, 1, 1, 1, 1),
 ( 4, 1, 1, 1, 1, 1, 1),
 ( 5, 1, 1, 1, 1, 1, 1),
 ( 6, 1, 1, 1, 1, 1, 1),
 ( 7, 1, 1, 1, 1, 1, 1),
 ( 8, 1, 1, 1, 1, 1, 1),
 ( 9, 1, 1, 1, 1, 1, 1),
 ( 10, 1, 1, 1, 1, 1, 1),
 ( 11, 1, 1, 1, 1, 1, 1),
 ( 12, 1, 1, 1, 1, 1, 1),
 ( 13, 1, 1, 1, 1, 1, 1),
 ( 14, 1, 1, 1, 1, 1, 1),
 ( 15, 1, 1, 1, 1, 1, 1),
 ( 16, 1, 1, 1, 1, 1, 1),
 ( 17, 1, 1, 1, 1, 1, 1),
 ( 18, 1, 1, 1, 1, 1, 1),
 ( 19, 1, 1, 1, 1, 1, 1);