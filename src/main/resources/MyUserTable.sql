drop table User;
create table User
(
    id       INTEGER not null
        constraint User_PK
            primary key,
    name     TEXT    not null,
    email    TEXT    not null,
    birthday TEXT    not null
);
INSERT INTO User (id,name,email,birthday) VALUES
	 (1,'John Deer','johndeer@jd.com','1967, 06, 22'),
	 (2,'Paul Shon','paulshon@jd.com','1978, 05, 12'),
	 (3,'John Rise','johnRise@jd.com','1985, 10, 13');
