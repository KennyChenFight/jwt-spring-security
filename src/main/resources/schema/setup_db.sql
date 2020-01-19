ALTER DATABASE jwtspringsecurity_db SET timezone TO 'UTC';

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
REVOKE CREATE ON SCHEMA public FROM PUBLIC;

GRANT USAGE ON SCHEMA public to jwtspringsecurity_admin;
GRANT CREATE ON SCHEMA public to jwtspringsecurity_admin;