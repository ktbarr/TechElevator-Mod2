package com.techelevator.projects;

import java.sql.Date;
import java.time.LocalDate;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Part5ProjectEmployees
{
    JdbcTemplate jdbcTemplate;

    public Part5ProjectEmployees()
    {
        BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void run()
    {
    	// ****************************************
        // Part 5 - Assigning Employees to Projects
    	// ****************************************
    	
    	// 5.1 - Assign Employees to Projects
    	System.out.println("*** 5.1 Adding employees to projects ***\n");        
        // TODO: add all employees projects (add all in the screen shot - you can add some of your own)
    	assignEmployeeToProject(1,3);  
    	assignEmployeeToProject(1,5);  
    	assignEmployeeToProject(3,1);  
    	assignEmployeeToProject(3,5);  
    	assignEmployeeToProject(3,7);  
    	assignEmployeeToProject(4,2);  
    	assignEmployeeToProject(4,6);  
    	assignEmployeeToProject(5,8);  
    	assignEmployeeToProject(5,9);  
    	assignEmployeeToProject(6,5);  
    	assignEmployeeToProject(6,10);  
    	assignEmployeeToProject(6,11);  
    	assignEmployeeToProject(7,13); 	

    	
    	// 5.2 - Get Employees by Project
    	System.out.println("\n*** 5.2 Get Employees by Project ***\n");        
        // TODO: display all employess for a project
    	getEmployeesByProject(1);    
    	getEmployeesByProject(6);     
    	getEmployeesByProject(7);   
    	getEmployeesByProject(8);     		

    	
    	// 5.3 - Get Projects by Employee
    	System.out.println("\n*** 5.3 Get Projects by Employee ***\n");        
        // TODO: display all projects for an employee
    	getProjectsByEmployee(1);    
    	getProjectsByEmployee(5);    
    	getProjectsByEmployee(12);   
    	getProjectsByEmployee(13);  
    	

    	
    	// 5.4 - Remove Employee from Project
    	System.out.println("\n*** 5.4 Remove Employee from Project ***\n");        
        // TODO: remove an employee from a project
    	removeEmployeeFromProject(7, 13);
    	// TODO: verify that it worked
    	getEmployeesByProject(7);   
    	getProjectsByEmployee(13); 
    	
    	
        //**********************
        // BONUS
        //**********************   	

    	
    	// 5.5 - Get all unassigned projects
    	System.out.println("\n*** 5.5 BONUS - Get all unassigned projects ***\n");        
        // TODO: display all projects that have no assignments
    	getProjectsWithNoEmployees();

    	
    	// 5.5 - Get employees with no projects
    	System.out.println("\n*** 5.6 BONUS - Get employees with no projects ***\n");        
        // TODO: get all employees who have not been assigned a project
    	getEmployeesWithNoProjects();
    	
    }


    //*************************************
    //PART 5 - Adding Employees to Projects
    //*************************************
    
    private void assignEmployeeToProject(int projectId, int employeeId)
    {
    	try
		{
			String sql = "INSERT INTO project_employee "
					+ "( "
					+ "		project_id "
					+ "		, employee_id "
					+ ") "
					+ "VALUES (?, ?); ";
			
			jdbcTemplate.update(sql, projectId, employeeId);
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error adding an employee to a project.");
			System.err.println(e.getMessage());
			System.out.println();
		}
    }
    
    private void removeEmployeeFromProject(int projectId, int employeeId)
    {
    	try
		{
			String sql = "DELETE FROM project_employee "
					+ "WHERE project_id = ? "
					+ "		AND employee_id = ?; ";
			
			jdbcTemplate.update(sql, projectId, employeeId);
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error removing an employee from a project.");
			System.err.println(e.getMessage());
			System.out.println();
		}
    }
    
    private void getEmployeesByProject(int projectId)
    {
    	try
		{
			// build the SQL SELECT statement (hint: do it in DBVisualizer first)
    		String sql = "SELECT "
    				+ "		e.employee_id "
    				+ "		, e.department_id "
    				+ "		, e.first_name "
    				+ "		, e.last_name "
    				+ "		, e.birth_date "
    				+ "		, e.gender "
    				+ "		, e.hire_date "
    				+ "FROM employee AS e "
    				+ "INNER JOIN project_employee as pe "
    				+ "		ON e.employee_id = pe.employee_id "
    				+ "WHERE pe.project_id = ?;";
    		
    		// use jdbcTemplate to execute the query - it returns a SqlRowSet
    		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, projectId);
    		
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
			System.err.println("There was an error selecting employees by project: \n" + e.getMessage());
		}
    }

    private void getProjectsByEmployee(int employeeId)
    {
    	// this will require a JOIN

    	try
		{
			String sql = "SELECT p.project_id "
					+ "		, p.name "
					+ "		, p.from_date "
					+ "		, p.to_date "
					+ "FROM project as p "
    				+ "INNER JOIN project_employee as pe "
    				+ "		ON p.project_id = pe.project_id "
    				+ "WHERE pe.employee_id = ?;";
			
			SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, employeeId);
			
			while(rows.next())
			{
				// Date is a dataType in java.sql.Date
				Date dbStartDate = rows.getDate("from_date");
				Date dbEndDate = rows.getDate("to_date");
				
				int projectId = rows.getInt("project_id");
				String name = rows.getString("name");
				LocalDate startDate = (dbStartDate == null) 
										? null 
										: dbStartDate.toLocalDate();
				LocalDate endDate = (dbEndDate == null) 
										? null 
										: dbEndDate.toLocalDate();
				
				System.out.println(projectId + ": " + name + "  " + startDate + "  -  " + endDate);
			}
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error getting projects by employee");
			System.err.println(e.getMessage());
			System.out.println();
		}
    }
    
    
    //**********************
    // BONUS
    //**********************

    private void getProjectsWithNoEmployees()
    {
    	// this will require an OUTER JOIN
    	try
		{
			String sql = "SELECT p.project_id "
					+ "		, p.name "
					+ "		, p.from_date "
					+ "		, p.to_date "
					+ "FROM project as p "
    				+ "LEFT OUTER JOIN project_employee as pe "
    				+ "		ON p.project_id = pe.project_id "
    				+ "WHERE pe.project_id IS NULL;";
			
			SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
			
			while(rows.next())
			{
				// Date is a dataType in java.sql.Date
				Date dbStartDate = rows.getDate("from_date");
				Date dbEndDate = rows.getDate("to_date");
				
				int projectId = rows.getInt("project_id");
				String name = rows.getString("name");
				LocalDate startDate = (dbStartDate == null) 
										? null 
										: dbStartDate.toLocalDate();
				LocalDate endDate = (dbEndDate == null) 
										? null 
										: dbEndDate.toLocalDate();
				
				System.out.println(projectId + ": " + name + "  " + startDate + "  -  " + endDate);
			}
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error getting projects with no employees");
			System.err.println(e.getMessage());
			System.out.println();
		}
    }

    private void getEmployeesWithNoProjects()
    {
    	// this will require an OUTER JOIN

    	try
		{
			// build the SQL SELECT statement (hint: do it in DBVisualizer first)
    		String sql = "SELECT "
    				+ "		e.employee_id "
    				+ "		, e.department_id "
    				+ "		, e.first_name "
    				+ "		, e.last_name "
    				+ "		, e.birth_date "
    				+ "		, e.gender "
    				+ "		, e.hire_date "
    				+ "FROM employee AS e "
    				+ "LEFT OUTER JOIN project_employee as pe "
    				+ "		ON e.employee_id = pe.employee_id "
    				+ "WHERE pe.employee_id IS NULL;";
    		
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
			System.err.println("There was an error getting employees with no projects: \n" + e.getMessage());
		}
    }


}
