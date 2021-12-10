DROP TABLE user;
CREATE TABLE user
(
    user_id         INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name TEXT not null,
    last_name  TEXT not null,
    email      TEXT not null,
    birthday   TEXT not null,
    username   TEXT not null,
    password   TEXT not null
);

INSERT INTO User (first_name, last_name, email, birthday,username, password)
VALUES ('John', 'Deer', 'johndeer@jd.com', '1967-06-22', 'johndeer', '12345678'),
       ('Paul', 'Shon', 'paulshon@jd.com', '1978-05-12','paulshon','87654321'),
       ('John', 'Rise', 'johnRise@jd.com', '1985-10-13','johnRise','43215678');
