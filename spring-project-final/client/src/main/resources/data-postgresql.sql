--


INSERT INTO users(user_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled) VALUES
('admin', '$2y$10$4cyMqF3jykO0CasWiUOIdOaymtshZ7sHrBfBYKG7V/xBM2AW/S1v6', 1, 1, 1, 1),
('user', '$2y$10$xKJ8w67HFKdv/5R3gtChS.A7DT.CmzXqF/rv1tdC.zmjhQURiIM8i', 1, 1, 1, 1);

INSERT INTO authorities (user_id, authority) VALUES
(CAST( 1 AS BIGINT), 'ROLE_ADMIN'),
(CAST( 2 AS BIGINT), 'ROLE_USER'),
(CAST( 1 AS BIGINT), 'ROLE_EDITOR');                                 ;

INSERT INTO acl_sid (id, principal, sid /* username or role */) VALUES
--  (1, 1, 'admin'),
--  (2, 1, 'user'),
 (CAST (1 AS BIGINT), 0, 'ROLE_ADMIN'),
 (CAST (2 AS BIGINT), 0, 'ROLE_USER'),
 (CAST (3 AS BIGINT), 0, 'ROLE_EDITOR');

INSERT INTO acl_class (id, class) VALUES
(CAST (1 AS BIGINT), 'ru.otus.domain.Book'),
(CAST (2 AS BIGINT), 'ru.otus.domain.Author'),
(CAST (3 AS BIGINT), 'ru.otus.domain.Genre'),
(CAST (4 AS BIGINT), 'ru.otus.domain.Comment');


INSERT INTO acl_object_identity (id, object_id_class /*acl_class(id) */, object_id_identity, parent_object, owner_sid /* acl_sid(id)*/, entries_inheriting) VALUES
(1, 1, 1, NULL, 1, 0),
(2, 2, 1, NULL, 2, 0),
(3, 3, 1, NULL, 1, 0),
(4, 4, 1, NULL, 2, 0),
(5, 1, 2, NULL, 1, 0),
(6, 2, 2, NULL, 2, 0),
(7, 3, 2, NULL, 1, 0),
(8, 4, 2, NULL, 2, 0);

INSERT INTO acl_entry ( acl_object_identity /*acl_object_identity(id) */, ace_order, sid /*ROLE_ID or USER_ID*/, mask, granting, audit_success, audit_failure) VALUES
 ( 1, 1, 1, 1, 1, 1, 1),
( 2, 2, 1, 1, 1, 1, 1),
( 3, 3, 1, 1, 1, 1, 1),
( 4, 4, 1, 1, 1, 1, 1),
 ( 5, 1, 1, 3, 1, 1, 1),
 ( 6, 2, 1, 3, 1, 1, 1),
 ( 7, 3, 1, 3, 1, 1, 1),
 ( 8, 4, 1, 3, 1, 1, 1);
-- ( 9, 1, 1, 1, 1, 1, 1),
-- ( 10, 1, 1, 1, 1, 1, 1),
-- ( 11, 1, 1, 1, 1, 1, 1),
-- ( 12, 1, 1, 1, 1, 1, 1),
-- ( 13, 1, 1, 1, 1, 1, 1),
-- ( 14, 1, 1, 1, 1, 1, 1),
-- ( 15, 1, 1, 1, 1, 1, 1),
-- ( 16, 1, 1, 1, 1, 1, 1),
-- ( 17, 1, 1, 1, 1, 1, 1),
-- ( 18, 1, 1, 1, 1, 1, 1),
-- ( 19, 1, 1, 1, 1, 1, 1);