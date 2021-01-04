CREATE TABLE test.person (
    u_id int IDENTITY(1,1) NOT NULL,
    u_name varchar(32),
    u_age int,
    CONSTRAINT person_pk PRIMARY KEY (u_id)
);