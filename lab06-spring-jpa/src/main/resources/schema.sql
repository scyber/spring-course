--DROP TABLE IF EXISTS BOOKS CASCADE;
--DROP TABLE IF EXISTS GENRES CASCADE;
--DROP TABLE IF EXISTS AUTHORS CASCADE;

CREATE TABLE AUTHORS(
id bigint auto_increment primary key,
name varchar(255));

CREATE TABLE GENRES(
id bigint auto_increment primary key,
name varchar(255));

CREATE TABLE BOOKS(
id bigint auto_increment primary key,
name varchar(255),
author_id bigint references authors(id),
genre_id bigint references genres(id)
);

CREATE TABLE COMMENTS(
id bigint auto_increment primary key,
book_id bigint references books(id) on delete cascade,
title varchar(255)
);
