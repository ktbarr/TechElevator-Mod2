BEGIN TRANSACTION;

CREATE TABLE student
(
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    state CHAR(2) NOT NULL,
    class VARCHAR(20) NOT NULL
);

INSERT INTO student (first_name, last_name, state, class) VALUES ('Monica','Carlo','PA','Java');
INSERT INTO student (first_name, last_name, state, class) VALUES ('Doug','Marshal','PA','C#');
INSERT INTO student (first_name, last_name, state, class) VALUES ('Greg','Bishop','OH','C#');
INSERT INTO student (first_name, last_name, state, class) VALUES ('Betty','Raines','OH','Java');
INSERT INTO student (first_name, last_name, state, class) VALUES ('Ted','Dorsey','PA','Java');
INSERT INTO student (first_name, last_name, state, class) VALUES ('Susan','Granger','PA','C#');

COMMIT TRANSACTION;