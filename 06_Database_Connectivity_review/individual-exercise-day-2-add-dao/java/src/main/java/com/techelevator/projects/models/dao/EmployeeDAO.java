package com.techelevator.projects.models.dao;

import java.util.List;

import com.techelevator.projects.models.Employee;

public interface EmployeeDAO
{
	Employee getEmployee(int id);
	List<Employee> getAllEmployees();
	List<Employee> searchByName(String firstName, String lastName);
	Employee addEmployee(Employee employee);
	void updateEmployee(int id, Employee employee);
	void deleteEmployee(int id);
}
