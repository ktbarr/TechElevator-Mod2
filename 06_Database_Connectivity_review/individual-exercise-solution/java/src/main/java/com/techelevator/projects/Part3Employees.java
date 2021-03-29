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
        addEmployee(2, 1, "Flo", "Henderson",LocalDate.parse("1990-12-28"), "F",LocalDate.parse("2011-08-01"));
        addEmployee(3, 2, "Franklin", "Trumbauer", LocalDate.parse("1980-07-14"), "M", LocalDate.parse("1998-09-01"));
        addEmployee(4, 2, "Delora", "Coty", LocalDate.parse("1976-07-04"), "F", LocalDate.parse("2009-03-01"));
        addEmployee(5, 2, "Sid", "Goodman", LocalDate.parse("1972-06-04"), "F", LocalDate.parse("1998-09-01"));
        addEmployee(6, 3, "Mary Lou", "Wolinski", LocalDate.parse("1983-04-08"), "F", LocalDate.parse("2012-04-01"));
        addEmployee(7, 3, "Jammie", "Mohl", LocalDate.parse("1987-11-11"), "M", LocalDate.parse("2013-07-01"));
        addEmployee(8, 3, "Neville", "Zellers", LocalDate.parse("1983-04-04"), "M", LocalDate.parse("2013-07-01"));
        addEmployee(9, 3, "Meg", "Buskirk", LocalDate.parse("1987-05-13"), "F", LocalDate.parse("2007-11-01"));
        addEmployee(10, 3, "Matthew", "Duford", LocalDate.parse("1968-04-05"), "M", LocalDate.parse("2016-02-16"));
        addEmployee(11, 4, "Jarred", "Lukach", LocalDate.parse("1981-09-25"), "M", LocalDate.parse("2003-05-01"));
        addEmployee(12, 4, "Gabriela", "Christie", LocalDate.parse("1980-03-17"), "F", LocalDate.parse("1999-08-01"));
        // add some of your own
        addEmployee(13, 5, "Johnny", "Lingo", LocalDate.parse("1974-05-22"), "M", LocalDate.parse("2006-03-15"));
        addEmployee(99, null, "Test", "Employee", LocalDate.parse("2001-12-01"), "M", LocalDate.parse("2020-12-01"));
        
                
        // 3.2 - select all employees from the database
        System.out.println("\n*** 3.2 Get all employees ***\n");
        getAllEmployees();
        
        
        // 3.3 - find an employee by id
        System.out.println("\n*** 3.3 Get employee by Id ***\n");
        //TODO: search for employees by id - try several ids - even some that don't exist
        getEmployeeById(7);
        getEmployeeById(13);
        getEmployeeById(99);
        getEmployeeById(0); //edge test
        getEmployeeById(14); //edge test
        
        
        // 3.4 - search for employee by name
        System.out.println("\n*** 3.4 Search for employee by name ***\n");
        searchForEmployeeByName("frank", "");
        // try some other names - make sure your search works
        searchForEmployeeByName("M", "");
        searchForEmployeeByName("fred", "kep");
        searchForEmployeeByName("", "lingo");
        
        
        // 3.5
        System.out.println("\n*** 3.5 Change employee data (UPDATE)***\n");
        //TODO: try changing the name, hireDate or any other bit of employee information
        // change name from Sid to Sidney and hiredate from 1998 to 1997
        updateEmployee(5, 2, "Sidney", "Goodman", LocalDate.parse("1972-06-04"), "F", LocalDate.parse("1997-09-01"));
        //TODO: verify that the update worked
        getEmployeeById(5);       

        
        // 3.6
        System.out.println("\n*** 3.6 Delete an employee***\n");
        deleteEmployee(99);
        //verify that the delete worked
        getEmployeeById(99);
    	
    }
    
    private void addEmployee(int employeeId, Integer departmentId, String firstName, String lastName, LocalDate birthDate, String gender, LocalDate hireDate)
    {
    	try
		{
			// build the SQL INSERT statement (hint: do it in DBVisualizer first)
    		String sql = "INSERT INTO employee "
    				+ "( "
    				+ "		employee_id "
    				+ "		, department_id "
    				+ "		, first_name "
    				+ "		, last_name "
    				+ "		, birth_date "
    				+ "		, gender "
    				+ "		, hire_date "
    				+ ") "
    				+ "VALUES (?, ?, ?, ?, ?, ?, ?); ";
    		
    		// use jdbcTemplate to execute the statement
    		jdbcTemplate.update(sql
    				, employeeId
    				, departmentId
    				, firstName
    				, lastName
    				, birthDate
    				, gender
    				, hireDate); 
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error inserting employee: " + employeeId);
			System.err.println(e.getMessage());
			System.out.println();
		}
    }
    
    private void getAllEmployees()
    {
    	try
		{
			// build the SQL SELECT statement (hint: do it in DBVisualizer first)
    		String sql = "SELECT "
    				+ "		employee_id "
    				+ "		, department_id "
    				+ "		, first_name "
    				+ "		, last_name "
    				+ "		, birth_date "
    				+ "		, gender "
    				+ "		, hire_date "
    				+ "FROM employee;";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
    		
    		// loop through the rows
    		while(rows.next())
    		{
    			// get the values from the columns
    			int employeeId = rows.getInt("employee_id");
    			// get all the other employee information
    			Integer departmentId = rows.getInt("department_id"); //Integer because department could be null
    			String firstName = rows.getString("first_name");
    			String lastName = rows.getString("last_name");
    			LocalDate birthDate = rows.getDate("birth_date").toLocalDate();
    			String gender = rows.getString("gender");
    			LocalDate hireDate = rows.getDate("hire_date").toLocalDate();
    			
    			// print the employee information
    			System.out.println(employeeId + ":  "
    					+ departmentId + "     "
    					+ firstName + " " + lastName + "     "
    					+ gender + "     "
    					+ birthDate + "     "
    					+ hireDate
    					);
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
    		String sql = "SELECT "
    				+ "		employee_id "
    				+ "		, department_id "
    				+ "		, first_name "
    				+ "		, last_name "
    				+ "		, birth_date "
    				+ "		, gender "
    				+ "		, hire_date "
    				+ "FROM employee "
    				+ "WHERE employee_id = ?;";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, employeeId);
    		
    		// check for an employee
    		if(rows.next())
    		{
    			// get the values from the columns
    			Integer departmentId = rows.getInt("department_id"); //Integer because department could be null
    			String firstName = rows.getString("first_name");
    			String lastName = rows.getString("last_name");
    			LocalDate birthDate = rows.getDate("birth_date").toLocalDate();
    			String gender = rows.getString("gender");
    			LocalDate hireDate = rows.getDate("hire_date").toLocalDate();
    			
    			// print the employee information
    			System.out.println(employeeId + ":  "
    					+ departmentId + "     "
    					+ firstName + " " + lastName + "     "
    					+ gender + "     "
    					+ birthDate + "     "
    					+ hireDate
    					);
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
    		String sql = "SELECT "
    				+ "		employee_id "
    				+ "		, department_id "
    				+ "		, first_name "
    				+ "		, last_name "
    				+ "		, birth_date "
    				+ "		, gender "
    				+ "		, hire_date "
    				+ "FROM employee "
    				+ "WHERE first_name ILIKE ? "
    				+ "		AND last_name ILIKE ?;";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql
														, "%" + firstName + "%"
														, "%" + lastName + "%");
    		
    		// loop through the rows
    		while(rows.next())
    		{
    			// get the values from the columns
    			int employeeId = rows.getInt("employee_id");
    			// get all the other employee information
    			Integer departmentId = rows.getInt("department_id"); //Integer because department could be null
    			String firstNameFromDb = rows.getString("first_name");
    			String lastNameFromDb = rows.getString("last_name");
    			LocalDate birthDate = rows.getDate("birth_date").toLocalDate();
    			String gender = rows.getString("gender");
    			LocalDate hireDate = rows.getDate("hire_date").toLocalDate();
    			
    			// print the employee information
    			System.out.println(employeeId + ":  "
    					+ departmentId + "     "
    					+ firstNameFromDb + " " + lastNameFromDb + "     "
    					+ gender + "     "
    					+ birthDate + "     "
    					+ hireDate
    					);
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
    		String sql = "UPDATE employee "
    				+ "SET department_id = ? "
    				+ "		, first_name = ? "
    				+ "		, last_name = ? "
    				+ "		, birth_date = ? "
    				+ "		, gender = ? "
    				+ "		, hire_date = ? "
    				+ "WHERE employee_id = ?";
    		
    		// use jdbcTemplate to execute the statement
    		jdbcTemplate.update(sql
    				, departmentId
    				, firstName
    				, lastName
    				, birthDate
    				, gender
    				, hireDate
    				, employeeId); // make sure that you add the remaining employee variables as parameters
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
    		String sql = "DELETE FROM employee WHERE employee_id = ?; ";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		jdbcTemplate.update(sql, employeeId);
    		    		
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error deleting employee: " + employeeId);
		}
    }


}
