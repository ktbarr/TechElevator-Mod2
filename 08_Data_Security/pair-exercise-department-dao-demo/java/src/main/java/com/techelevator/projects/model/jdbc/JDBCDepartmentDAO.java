package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.interfaces.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) 
	{
		// 1: connect - DONE!
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments()
	{
		List<Department> departments = new ArrayList<Department>();		
		
		// 2: execute a SQL - will give a result - SPEAK SQL
		String sql = "SELECT department_id\r\n" + 
					"        , name\r\n" + 
					"FROM department;";
		
		// 3: convert the result - into something that the JAVA APPLICATION can use - Translate to JAVA - NO MORE SQL
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		
		/*
		 *  department_id	name
		    1				Department of Redundancy Department
		 -	2				Network Administration
			3				Research and Development
			4				Store Support
		 */
		while(rows.next())
		{
			long id = rows.getLong("department_id");
			String name = rows.getString("name");
			
			Department department = new Department();
			department.setId(id);
			department.setName(name);
			
			// add the department to the return list
			departments.add(department);
		}
		
		// return Java worthy objects
		
		return departments;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) 
	{
		List<Department> departments = new ArrayList<Department>();		
		
		// 2: execute a SQL - will give a result - SPEAK SQL
		String sql = "SELECT department_id\r\n" + 
						"        , name\r\n" + 
						"FROM department\r\n" + 
						"WHERE name ILIKE ?;";
		
		// 3: convert the result - into something that the JAVA APPLICATION can use - Translate to JAVA - NO MORE SQL
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, nameSearch);
		
		
		while(rows.next())
		{
			long id = rows.getLong("department_id");
			String name = rows.getString("name");
						
			Department department = new Department();
			department.setId(id);
			department.setName(name);
			
			// add the department to the return list
			departments.add(department);
			
		}
		
		// return Java worthy objects
		
		return departments;
	}

	@Override
	public void saveDepartment(Department updatedDepartment) 
	{
		// the application has made changes to the a department
		// now I need to save them to the database
		
		// speak SQL
		String sql = "UPDATE department\r\n" + 
					"SET name = ?\r\n" + 
					"WHERE department_id = ?;";
		
		jdbcTemplate.update(sql
						, updatedDepartment.getName()
						, updatedDepartment.getId());
		
		// convert the u
		
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		return null;
	}

	@Override
	public Department getDepartmentById(Long id) {
		return null;
	}

}
