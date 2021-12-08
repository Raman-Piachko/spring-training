DROP TABLE User;
CREATE TABLE user
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT not null,
    last_name  TEXT not null,
    email      TEXT not null,
    birthday   TEXT not null
);

INSERT INTO User (first_name, last_name, email, birthday)
VALUES ('John', 'Deer', 'johndeer@jd.com', '1967-06-22'),
       ('Paul', 'Shon', 'paulshon@jd.com', '1978-05-12'),
       ('John', 'Rise', 'johnRise@jd.com', '1985-10-13');
