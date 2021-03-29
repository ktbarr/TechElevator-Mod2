package com.techelevator.projects.models.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.models.Department;
import com.techelevator.projects.models.dao.DepartmentDAO;

public class JDBCDepartmentDAO implements DepartmentDAO
{
    JdbcTemplate jdbcTemplate;

    public JDBCDepartmentDAO()
    {
        BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public Department getDepartment(int id)
	{
		Department department = null;
		
		String sql = "SELECT department_id "
        		+ "		, name "
        		+ "FROM department "
        		+ "WHERE department_id = ?; ";
        
        // use jdbcTemplate to execute the statement (run the query) - make sure to include the id
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);
        
        if(rows.next())
        {
        	department = mapRowToDepartment(rows);
        }
        
        return department;
	}

	@Override
	public List<Department> getAllDepartments()
	{
		List<Department> departments = new ArrayList<Department>();
		
		String sql = "SELECT department_id "
        		+ "		, name "
        		+ "FROM department;";
        
        // use jdbcTemplate to execute the statement (run the query)
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
        
        while(rows.next())
        {
        	// mapwRowToDepartments is a function that I created 
        	// so that I don't have to repeat this logic
        	Department department = mapRowToDepartment(rows);
        	departments.add(department);
        }
		
		return departments;
	}

	@Override
	public List<Department> searchByName(String name)
	{
		List<Department> departments = new ArrayList<Department>();
		
		String sql = "SELECT department_id "
        		+ "		, name "
        		+ "FROM department "
        		+ "WHERE name ILIKE ?; ";
        
        System.out.println(sql);
        
        // use jdbcTemplate to execute the statement (run the query) - make sure to include the id
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, "%" + name + "%");
        
        System.out.println("-- Search results for department: " + name + " --");
        while(rows.next())
        {
        	Department department = mapRowToDepartment(rows);
        	departments.add(department);
        }
        
        return departments;
	}

	@Override
	public Department addDepartment(Department department)
	{			
        // create the SQL statement (hint: use DBVisualizer to test the SQL first)
        String sql = "INSERT INTO department "
	                + "( " 
	                + "     department_id " 
	                + "     , name " 
	                + ") "
	                + "VALUES (?, ?); ";

        // use the jdbcTemplate to execute the statement
        jdbcTemplate.update(sql
			        		, department.getId()
			        		, department.getName());
        
        return department;
	}

	@Override
	public void updateDepartment(int id, Department department)
	{
		 String sql = "UPDATE department "
	                + "SET name = ? "
	                + "WHERE department_id = ?; ";

	     // use the jdbcTemplate to execute the statement
	     // notice that the order of the input variables 
	     // MUST match the order of the ? in the SQL statement
	     jdbcTemplate.update(sql
				        		, department.getName()
				        		, id);
			
	}

	@Override
	public void deleteDepartment(int id)
	{
		String sql = "DELETE FROM department WHERE department_id = ?";
			
		// use the jdbcTemplate to execute the statement
		jdbcTemplate.update(sql, id);
		
	}
	
	private Department mapRowToDepartment(SqlRowSet row)
	{
    	int id = row.getInt("department_id");
    	String name = row.getString("name");

    	return new Department(id, name);
	}

}
