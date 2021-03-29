-- Clean out the data first
DELETE FROM project_employee;
DELETE FROM employee;
DELETE FROM project;
DELETE FROM department;

-- Insert a fake department
INSERT INTO department (name) VALUES ('TEST DEPARTMENT');
DECLARE @departmentId int = (SELECT @@IDENTITY);

-- Insert a fake employee (that will be assigned to a project)
INSERT INTO employee (department_id, first_name, last_name, birth_date, gender, hire_date, job_title)
VALUES (@departmentId, 'Employee', '1', '1953-07-15', 'M', '2001-04-01', 'Chief Head Honcho');
DECLARE @assignedEmployeeId int = (SELECT @@IDENTITY);

-- Insert a fake employee (that will not get assigned to a project)
INSERT INTO employee (department_id, first_name, last_name, birth_date, gender, hire_date, job_title)
VALUES (@departmentId, 'Employee', '2', '1953-07-15', 'M', '2001-04-01', 'Chief Head Honcho');
DECLARE @unassignedEmployeeId int = (SELECT @@IDENTITY);

-- Insert a project
INSERT INTO project (name, from_date, to_date) VALUES ('TEST PROJECT', '1961-03-01', GETDATE());
DECLARE @projectId int = (SELECT @@IDENTITY);

-- Assign employee 1 to the project
INSERT INTO project_employee VALUES (@projectId, @assignedEmployeeId);

-- Return all of the Ids
SELECT 
	@departmentId as departmentId, 
	@assignedEmployeeId as assignedEmployeeId, 
	@unassignedEmployeeId as unassignedEmployeeId, 
	@projectId as projectId;
