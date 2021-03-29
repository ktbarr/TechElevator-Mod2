package com.techelevator.projects.models.dao;

import java.util.List;

import com.techelevator.projects.models.Department;

public interface DepartmentDAO
{
	Department getDepartment(int id);
	List<Department> getAllDepartments();
	List<Department> searchByName(String name);
	Department addDepartment(Department department);
	void updateDepartment(int id, Department department);
	void deleteDepartment(int id);
}
