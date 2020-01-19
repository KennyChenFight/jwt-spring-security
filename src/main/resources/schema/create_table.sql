--the script to remove all tables in the database

DROP TABLE IF EXISTS users CASCADE;

create table users
(
    id uuid,
    email character varying(200) not null,
    password_digest character varying(1000) not null,
    name character varying(255) not null,

    CONSTRAINT "users_pk" PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT users_u1 UNIQUE (email);
