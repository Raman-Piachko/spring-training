
create table User
(
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    name     TEXT not null,
    email    TEXT not null,
    birthday TEXT not null
);

INSERT INTO User ( name, email, birthday)
VALUES ( 'John Deer', 'johndeer@jd.com', '1967, 06, 22'),
       ( 'Paul Shon', 'paulshon@jd.com', '1978, 05, 12'),
       ( 'John Rise', 'johnRise@jd.com', '1985, 10, 13');
