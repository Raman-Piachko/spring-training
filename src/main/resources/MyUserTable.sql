CREATE DATABASE myDataBase;
use myDataBase;
CREATE TABLE MyUserTable (
                             ID NUMERIC NOT NULL,
                             name TEXT NOT NULL,
                             email TEXT NOT NULL,
                             birthday NUMERIC NOT NULL,
                             CONSTRAINT User_PK PRIMARY KEY (ID)
);
INSERT INTO MyUserTable (id,name,email,birthday) VALUES
	 (1,'John Deer','johndeer@jd.com',1967, 06, 22),
	 (2,'Paul Shon','paulshon@jd.com',1978, 05, 12),
	 (3,'John Deer','johnRise@jd.com',1985, 10, 13);

COMMIT;