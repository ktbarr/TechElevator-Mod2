package com.techelevator.projects;

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
    	//assignEmployeeToProject(projectId, employeeId);    	

    	
    	// 5.2 - Get Employees by Project
    	System.out.println("*** 5.2 Get Employees by Project ***\n");        
        // TODO: display all employess for a project
    	//getEmployeesByProject(projectId);    	

    	
    	// 5.3 - Get Projects by Employee
    	System.out.println("*** 5.3 Get Projects by Employee ***\n");        
        // TODO: display all projects for an employee
    	//getProjectsByEmployee(employeeId);    	

    	
    	// 5.4 - Remove Employee from Project
    	System.out.println("*** 5.4 Remove Employee from Project ***\n");        
        // TODO: remove an employee from a project
    	//removeEmployeeFromProject(projectId, employeeId);
    	// TODO: verify that it worked
    	//getEmployeesByProject(projectId); 
    	
    	
        //**********************
        // BONUS
        //**********************   	

    	
    	// 5.5 - Get all unassigned projects
    	System.out.println("*** 5.5 BONUS - Get all unassigned projects ***\n");        
        // TODO: display all projects that have no assignments
    	getProjectsWithNoEmployees();

    	
    	// 5.5 - Get employees with no projects
    	System.out.println("*** 5.6 BONUS - Get employees with no projects ***\n");        
        // TODO: get all employees who have not been assigned a project
    	getEmployeesWithNoProjects();
    	
    }


    //*************************************
    //PART 5 - Adding Employees to Projects
    //*************************************
    
    private void assignEmployeeToProject(int projectId, int employeeId)
    {
    	
    }
    
    private void removeEmployeeFromProject(int projectId, int employeeId)
    {
    	
    }
    
    private void getEmployeesByProject(int projectId)
    {
    	// this will require a JOIN
    }

    private void getProjectsByEmployee(int employeeId)
    {
    	// this will require a JOIN
    }
    
    
    //**********************
    // BONUS
    //**********************

    private void getProjectsWithNoEmployees()
    {
    	// this will require an OUTER JOIN
    }

    private void getEmployeesWithNoProjects()
    {
    	// this will require an OUTER JOIN
    }


}
