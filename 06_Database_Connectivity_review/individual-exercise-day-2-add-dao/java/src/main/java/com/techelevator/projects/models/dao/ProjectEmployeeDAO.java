package com.techelevator.projects.models.dao;

import java.util.List;

import com.techelevator.projects.models.Employee;
import com.techelevator.projects.models.Project;

public interface ProjectEmployeeDAO
{
	void assignEmployeeToProject(int projectId, int employeeId);
	void removeEmployeeFromProject(int projectId, int employeeId);
	List<Employee> getEmployeesByProject(int projectId);
	List<Project> getProjectsByEmployee(int employeeId);
	List<Employee> getEmployeesWithoutProjects();
	List<Project> getProjectsWithoutEmployees();

}
