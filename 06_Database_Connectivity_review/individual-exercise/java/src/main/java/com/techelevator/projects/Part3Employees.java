package com.techelevator.projects;

import java.time.LocalDate;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Part3Employees
{
    JdbcTemplate jdbcTemplate;

    public Part3Employees()
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
        // Part 3 - Working with employees
    	// *********************************
    	
    	// 3.1 - add all departments to the database
    	System.out.println("*** 3.1 Adding employees ***\n");        
        // TODO: add all employees
        addEmployee(1, null, "Fredrick", "Keppard", LocalDate.parse("1953-07-15"), "M", LocalDate.parse("2001-04-01"));
        // add the rest
        // add some of your own
        
                
        // 3.2 - select all departments from the database
        System.out.println("\n*** 3.2 Get all employees ***\n");
        getAllEmployees();
        
        
        // 3.3 - find a department by id
        System.out.println("\n*** 3.3 Get employee by Id ***\n");
        //TODO: search for employees by id - try several ids - even some that don't exist
        getEmployeeById(7);
        
        
        // 3.4 - search for department by name
        System.out.println("\n*** 3.4 Search for employee by name ***\n");
        searchForEmployeeByName("frank", "");
        // try some other names - make sure your search works
        
        
        // 3.5
        System.out.println("\n*** 3.5 Change employee data (UPDATE)***\n");
        //TODO: try changing the name, hireDate or any other bit of employee information
        //updateEmployee(employeeId, departmentId, firstName, lastName, birthDate, gender, hireDate);
        //TODO: verify that the update worked
        //getEmployeeById(employeeId);       

        
        // 3.6
        System.out.println("\n*** 3.6 Delete an employee***\n");
        //deleteEmployee(employeeId);
        //verify that the delete worked
        //getEmployeeById(employeeId);
    	
    }
    
    private void addEmployee(int employeeId, Integer departmentId, String firstName, String lastName, LocalDate birthDate, String gender, LocalDate hireDate)
    {
    	try
		{
			// build the SQL INSERT statement (hint: do it in DBVisualizer first)
    		String sql = "";
    		
    		// use jdbcTemplate to execute the statement
    		jdbcTemplate.update(sql
    				, employeeId
    				, departmentId
    				, firstName); // make sure that you add the remaining employee variables as parameters
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error inserting employee: " + employeeId);
		}
    }
    
    private void getAllEmployees()
    {
    	try
		{
			// build the SQL SELECT statement (hint: do it in DBVisualizer first)
    		String sql = "";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
    		
    		// loop through the rows
    		while(rows.next())
    		{
    			// get the values from the columns
    			int employeeId = rows.getInt("employee_id");
    			// get all the other employee information
    			
    			// print the employee information
    		}
    		
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error selecting all employees: " + e.getMessage());
		}
    }

    private void getEmployeeById(int employeeId)
    {
    	try
		{
			// build the SQL SELECT statement (hint: do it in DBVisualizer first)
    		String sql = "";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
    		
    		// check for an employee
    		if(rows.next())
    		{
    			// get the values from the columns
    			
    			// print the employee information
    		}
    		else 
    		{
				System.err.println("There is no employee with id: " + employeeId);
			}
    		
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error selecting all employees: " + e.getMessage());
		}
    }

    private void searchForEmployeeByName(String firstName, String lastName)
    {
    	try
		{
			// build the SQL SELECT statement (hint: do it in DBVisualizer first)
    		String sql = "";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
    		
    		// loop through the rows
    		while(rows.next())
    		{
    			// get the values from the columns
    			int employeeId = rows.getInt("employee_id");
    			// get all the other employee information
    			
    			// print the employee information
    		}
    		
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error searching for employee by name:\n " + e.getMessage());
		}
    }

    private void updateEmployee(int employeeId, Integer departmentId, String firstName, String lastName, LocalDate birthDate, String gender, LocalDate hireDate)
    {
    	try
		{
			// build the SQL INSERT statement (hint: do it in DBVisualizer first)
    		String sql = "";
    		
    		// use jdbcTemplate to execute the statement
    		jdbcTemplate.update(sql
    				, employeeId
    				, departmentId
    				, firstName); // make sure that you add the remaining employee variables as parameters
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error updating employee: " + employeeId);
		}
    }

    private void deleteEmployee(int employeeId)
    {
    	try
		{
			// build the SQL SELECT statement (hint: do it in DBVisualizer first)
    		String sql = "";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		jdbcTemplate.update(sql, employeeId);
    		    		
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error deleting employee: " + employeeId);
		}
    }


}
