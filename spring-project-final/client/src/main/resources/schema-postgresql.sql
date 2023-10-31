-- Security DDL

DROP TABLE IF EXISTS users CASCADE ;
DROP TABLE IF EXISTS authorities CASCADE ;
DROP TABLE IF EXISTS acl_sid CASCADE ;
DROP TABLE IF EXISTS acl_class cascade ;
DROP TABLE IF EXISTS acl_entry cascade ;
DROP TABLE IF EXISTS acl_object_identity cascade ;

create table IF NOT EXISTS users(
                    id bigserial,
                    user_name varchar(255) not null,
                    password varchar(255) not null,
                    account_non_expired     int,
                    account_non_locked      int,
                    credentials_non_expired int,
                    enabled int not null,
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
--- нужен parent_object REFERENCES acl_object_identity (id);
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