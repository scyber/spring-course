DROP TABLE IF EXISTS BOOKS CASCADE;
DROP TABLE IF EXISTS GENRES CASCADE;
DROP TABLE IF EXISTS AUTHORS CASCADE;
DROP TABLE IF EXISTS COMMENTS CASCADE;
DROP TABLE IF EXISTS AUTHORS_BOOKS cascade;
DROP TABLE IF EXISTS GENRES_BOOKS cascade;

DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS authorities CASCADE ;
DROP TABLE IF EXISTS acl_sid CASCADE ;
DROP TABLE IF EXISTS acl_class cascade ;
DROP TABLE IF EXISTS acl_entry cascade ;
DROP TABLE IF EXISTS acl_object_identity cascade ;


CREATE TABLE IF NOT EXISTS AUTHORS(
id bigserial primary key,
name varchar(255));

CREATE TABLE IF NOT EXISTS GENRES(
id bigserial primary key,
name varchar(255));

CREATE TABLE IF NOT EXISTS BOOKS(
id bigserial primary key,
title varchar(255),
author_id bigint references authors(id),
genre_id bigint references genres(id)
);
CREATE TABLE IF NOT EXISTS AUTHORS_BOOKS(
author_id bigint references authors(id),
book_id bigint references books(id));

CREATE TABLE IF NOT EXISTS GENRES_BOOKS(
genre_id bigint references genres(id),
book_id bigint references books(id));

CREATE TABLE IF NOT EXISTS COMMENTS(
id bigserial primary key,
book_id bigint references books(id) on delete cascade,
title varchar(255)
);
create table IF NOT EXISTS users(
                    id bigserial,
                    user_name varchar(255) not null,
                    password varchar(255) not null,
                    account_non_expired     boolean,
                    account_non_locked      boolean,
                    credentials_non_expired boolean,
                    enabled boolean not null,
                    primary key(id)
);

create table IF NOT EXISTS authorities (
                           id bigserial primary key,
                           user_id bigint references users(id) on delete cascade,
                           authority varchar(255) not null
);

CREATE TABLE IF NOT EXISTS acl_sid (
    id bigserial,
    principal smallint NOT NULL,
    sid varchar(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (sid,principal)
);

--
CREATE TABLE IF NOT EXISTS acl_class (
    id bigserial,
    class varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (class)
);
--- ToDo нужен ли parent_object REFERENCES acl_object_identity (id);
--
CREATE TABLE IF NOT EXISTS acl_object_identity (
    id bigserial,
    object_id_class bigint references acl_class(id), --CLASS NAME
    object_id_identity bigint NOT NULL,
    parent_object bigint DEFAULT NULL,
    owner_sid bigint references acl_sid(id), --ROLE ID/ USER ID
    entries_inheriting smallint NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (object_id_class,object_id_identity)
);
--
CREATE TABLE IF NOT EXISTS acl_entry (
    id bigserial,
    acl_object_identity bigint references acl_object_identity(id), -- class_id
    ace_order int NOT NULL,
    sid bigint references acl_sid(id), --ROLE ID / USER ID
    mask int NOT NULL,
    granting smallint NOT NULL,
    audit_success smallint NOT NULL,
    audit_failure smallint NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (acl_object_identity,ace_order)
);

