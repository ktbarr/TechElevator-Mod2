package com.techelevator.projects;

import java.sql.Date;
import java.time.LocalDate;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.models.dao.ProjectEmployeeDAO;
import com.techelevator.projects.models.jdbc.JDBCProjectEmployeeDAO;

public class Part5ProjectEmployees
{
	ProjectEmployeeDAO dao = new JDBCProjectEmployeeDAO();

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
    		//TODO: call the DAO to add the employee
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
			// TODO: call the DAO to remove the employee
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
    		//TODO: get the list of employees by project from the DAO
    		
    		//TODO: print the details of each employee
    		
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error selecting employees by project: \n" + e.getMessage());
		}
    }

    private void getProjectsByEmployee(int employeeId)
    {
    	try
		{
    		//TODO: get the list of projects from the DAO
    		
    		//TODO: print the details of each project
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
    		//TODO: get the list of projects from the DAO
    		
    		//TODO: print the details of each project
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
    		//TODO: get the list of employees by project from the DAO
    		
    		//TODO: print the details of each employee    		
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error getting employees with no projects: \n" + e.getMessage());
		}
    }


}
