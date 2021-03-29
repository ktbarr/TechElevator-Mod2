package com.techelevator.projects;

import java.time.LocalDate;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Part2Departments
{
    JdbcTemplate jdbcTemplate;

    public Part2Departments()
    {
        BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void run()
    {
    	// *********************************
        // Part 2 - Working with departments
    	// *********************************
    	
    	// 2.1 - add all departments to the database
    	System.out.println("*** 2.1 Adding departments ***\n");
        addDepartment(1, "Department of Redundancey Department"); // misspelled on purpose
        addDepartment(2, "Network Administration");        
        // TODO: add the remaining departments to the database        
        // TODO: add some of your own deparments - any name will do :)
        addDepartment(82, "Team Party Planners");
        
        
        // 2.2 - select all departments from the database
        System.out.println("\n*** 2.2 Displaying departments from the database ***\n");
        getAllDepartments();
        
        
        // 2.3 - find a department by id
        System.out.println("\n*** 2.3 Get department by Id ***\n");
        getDepartmentById(1);
        getDepartmentById(18);
        getDepartmentById(82);
        // try searching for some of your own
        
        // 2.4 - search for department by name
        System.out.println("\n*** 2.4 Search for department by name ***\n");
        searchForDepartmentByName("red");
        searchForDepartmentByName("Redundant");
        // try some other name searches
        
        
        // 2.5
        System.out.println("\n*** 2.5 Modify a department name (UPDATE)***\n");
        updateDepartment(1, "Department of Redundancy Department");
        //verify that the update worked
        getDepartmentById(1);       

        
        // 2.6
        System.out.println("\n*** 2.6 Delete the Party department***\n");
        deleteDepartment(82);
        //verify that the delete worked
        getDepartmentById(82);

    	
    }

    private void addDepartment(int departmentId, String departmentName)
    {
    	try
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
				        		, departmentId
				        		, departmentName);
		} 
    	catch (Exception e)
		{
			// if you run this application multiple times - we expect errors
    		// because you should not be able to add the same department more than once
    		System.err.println("There was an error adding the following department --> " + departmentId + ": " + departmentName);
		}

    }
    
    private void getAllDepartments()
    {
        // create the SQL statement (hint: use DBVisualizer to test the SQL first)
        String sql = "SELECT department_id "
        		+ "		, name "
        		+ "FROM department;";
        
        // use jdbcTemplate to execute the statement (run the query)
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
        
        while(rows.next())
        {
        	//get the values from the columns
        	//departmentId is the java variable name - it can be whatever I want to call it
        	//                            --> department_id is the column name in the database IT MUST MATCH what is in the database
        	int departmentId = rows.getInt("department_id");
        	String departmentName = rows.getString("name");
        	
        	System.out.println(departmentId + ": " + departmentName);
        }
    }

    private void getDepartmentById(int departmentId)
    {
        // create the SQL statement (hint: use DBVisualizer to test the SQL first)
        String sql = "SELECT department_id "
        		+ "		, name "
        		+ "FROM department "
        		+ "WHERE department_id = ?; ";
        
        // use jdbcTemplate to execute the statement (run the query) - make sure to include the id
        SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, departmentId);
        
        if(rows.next())
        {
        	//get the values from the columns (we don't need the departmentId - we already know it)
        	String departmentName = rows.getString("name");
        	
        	System.out.println(departmentId + ": " + departmentName);
        }
        else 
        {
			System.out.println("There is no department with id: " + departmentId);
		}
    }

    private void searchForDepartmentByName(String name)
    {
        // create the SQL statement (hint: use DBVisualizer to test the SQL first)
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
        	int departmentId = rows.getInt("department_id");
        	String departmentName = rows.getString("name");
        	
        	System.out.println(departmentId + ": " + departmentName);
        }
        System.out.println();
    }
    
    private void updateDepartment(int departmentId, String departmentName)
    {
    	try
		{				
	        // create the SQL statement (hint: use DBVisualizer to test the SQL first)
	        String sql = "UPDATE department "
		                + "SET name = ? "
		                + "WHERE department_id = ?; ";
	
	        // use the jdbcTemplate to execute the statement
	        // notice that the order of the input variables 
	        // MUST match the order of the ? in the SQL statement
	        jdbcTemplate.update(sql
				        		, departmentName
				        		, departmentId);
		} 
    	catch (Exception e)
		{
    		System.err.println("There was an error updating the following department --> " + departmentId + ": " + departmentName);
		}

    }

    private void deleteDepartment(int departmentId)
    {
    	try
		{				
	        // create the SQL statement (hint: use DBVisualizer to test the SQL first)
	        String sql = "DELETE FROM department WHERE department_id = ?";
	
	        // use the jdbcTemplate to execute the statement
	        jdbcTemplate.update(sql, departmentId);
		} 
    	catch (Exception e)
		{
    		System.err.println("There was an error deleting department --> " + departmentId);
		}
    }
    
  
}
