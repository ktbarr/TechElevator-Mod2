CREATE TABLE employee 
(
	employee_id integer NOT NULL PRIMARY KEY,
	department_id integer,
	first_name varchar(20) NOT NULL,
	last_name varchar(30) NOT NULL,
	birth_date date NOT NULL,
	gender char(1) NOT NULL,
	hire_date date NOT NULL
);

CREATE TABLE department 
(
	department_id integer NOT NULL PRIMARY KEY,
	name varchar(40) UNIQUE NOT NULL
);

CREATE TABLE project 
(
	project_id integer NOT NULL PRIMARY KEY,
	name varchar(40) UNIQUE NOT NULL,
	from_date date,
	to_date date
);

CREATE TABLE project_employee 
(
	project_id integer NOT NULL,
	employee_id integer NOT NULL,
	PRIMARY KEY (project_id, employee_id)
);

ALTER TABLE employee 
	ADD FOREIGN KEY (department_id) 
		REFERENCES department(department_id);

ALTER TABLE project_employee 
	ADD FOREIGN KEY (project_id) 
		REFERENCES project(project_id);

ALTER TABLE project_employee 
	ADD FOREIGN KEY (employee_id) 
		REFERENCES employee(employee_id);
