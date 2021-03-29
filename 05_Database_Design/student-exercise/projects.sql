-- DDL - data definition language
-- CREATE database, table,
-- DROP database, table
-- ALTER table

SELECT pg_terminate_backend(pid) FROM pg_start_activity WHERE datname = 'project_org';

-- removes database
DROP DATABASE project_org;

-- creates database
CREATE DATABASE project_org;


DROP TABLE department;
DROP TABLE employee;
DROP TABLE project;

CREATE TABLE department
(
        department_id CHAR(2) NOT NULL PRIMARY KEY
        , department_name VARCHAR(50) NOT NULL
);


CREATE TABLE employee
(
        employee_id SERIAL NOT NULL PRIMARY KEY
        , job_title VARCHAR(50) NOT NULL
        , last_name VARCHAR(50) NOT NULL
        , first_name VARCHAR(50) NOT NULL
        , gender VARCHAR(10) NULL
        , date_of_birth DATE NULL
        , date_of_hire DATE NOT NULL
        , department_id CHAR(2) NOT NULL
);

CREATE TABLE project
(
        project_id CHAR(6) NOT NULL PRIMARY KEY
        , project_name VARCHAR(150) NOT NULL
        , project_start DATE NOT NULL
        , employee_id INTEGER NULL
);

ALTER TABLE employee
ADD CONSTRAINT fk_department_employee
FOREIGN KEY (department_id)
REFERENCES department (department_id);


ALTER TABLE project
ADD CONSTRAINT fk_employee_project
FOREIGN KEY (employee_id)
REFERENCES employee (employee_id);




INSERT INTO department (department_id, department_name)
VALUES('RS', 'Registration Services');

INSERT INTO department (department_id, department_name)
VALUES('OD', 'Onsite Deployment');

INSERT INTO department (department_id, department_name)
VALUES('PM', 'Project Management');

INSERT INTO department (department_id, department_name)
VALUES('AM', 'Account Management');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Registration Specialist', 'White', 'Mallory', 'Female', '1992-09-22', '2015-01-15', 'RS');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Registration Specialist', 'Gutfranski', 'Holly', 'Female', '1986-10-26', '2012-02-15', 'RS');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Registration Specialist', 'Reagan', 'Brian', 'Male', '1982-04-18', '2009-03-15', 'RS');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Registration Specialist', 'King', 'Sandy', 'Female', '1973-07-11', '2006-04-15', 'RS');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Onsite Deployment Manager', 'Huster', 'Jared', 'Male', '1975-08-24', '2001-05-15', 'OD');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Onsite Deployment Manager', 'Dorwart', 'Dave', 'Male', '1976-05-18', '2002-06-15', 'OD');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Onsite Deployment Manager', 'McQuillen', 'Derek', 'Male', '1985-04-22', '2015-06-15', 'OD');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Onsite Deployment Manager', 'Thomas', 'Jordan', 'Male', '1988-09-07', '2016-07-15', 'OD');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Project Manager', 'Statler', 'Natalie', 'Female', '1981-12-15', '2012-08-15', 'PM');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Project Manager', 'Waite', 'Val', 'Female', '1978-11-17', '2013-09-15', 'PM');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Project Manager', 'Porten', 'Carrie', 'Female', '1981-10-14', '2014-10-15', 'PM');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Account Manager', 'Wallace', 'James', 'Male', '1962-01-31', '2000-11-15', 'AM');

INSERT INTO employee(job_title, last_name, first_name, gender, date_of_birth, date_of_hire, department_id)
VALUES('Account Manager', 'Cummings', 'Jenna', 'Female', '1980-04-12', '2004-12-15', 'AM');

INSERT INTO project(project_id, project_name, project_start, employee_id)
VALUES('ANE201', 'Anesthesiology Annual Conference 2021', '2021-04-01', 1);

INSERT INTO project(project_id, project_name, project_start, employee_id)
VALUES('AIH201', 'AIHce 2021', '2020-08-30', 2);

INSERT INTO project(project_id, project_name, project_start, employee_id)
VALUES('NCG201', 'Commodity Classic 2021', '2020-06-15', 3);

INSERT INTO project(project_id, project_name, project_start, employee_id)
VALUES('CON204', 'ConExpo/ConAg 2024', '2021-06-21', 6);

INSERT INTO project(project_id, project_name, project_start, employee_id)
VALUES('NRF202', 'National Retail Federation - Big Show', '2021-03-15', 5);


SELECT *
FROM department;

SELECT *
FROM employee;

SELECT *
FROM project;