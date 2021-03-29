package com.techelevator.projects;

import java.time.LocalDate;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Part4Projects
{
    JdbcTemplate jdbcTemplate;

    public Part4Projects()
    {
        BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void run()
    {
        //*********************************
        //Part 4 - Working with Projects
        //*********************************
    	
    	// 4.1 - add all projects to the database
    	System.out.println("*** 4.1 Adding projects ***\n");        
        // TODO: add all projects (add all in the screen shot - and add some of your own)
    	//addProject(projectId, projectName, startDate, endDate);
    	
        
                
        // 4.2 - select all projects from the database
        System.out.println("\n*** 4.2 Get all projects ***\n");
        //getAllProjects();
        
        
        // 4.3 - find a project by id
        System.out.println("\n*** 4.3 Get project by Id ***\n");
        //TODO: search for project by id - try several ids - even some that don't exist
        //getProjectById(projectId);
        
        
        // 4.4 - search for active projects
        System.out.println("\n*** 4.4 Search for active projects ***\n");
        // TODO: get active projects
        //getAllActiveProjects();
        
        
        // 4.5
        System.out.println("\n*** 4.5 Change project data (UPDATE)***\n");
        //TODO: try changing the name, startDate or any other bit of project information
        //updateProject(projectId, projectName, startDate, endDate);
        //TODO: verify that the update worked
        //getProjectById(projectId);      

        
        // 4.6
        System.out.println("\n*** 4.6 Delete a project***\n");
        //deleteProject(projectId);
        //verify that the delete worked
        //getProjectById(projectId); 
        
        
        

    	
    }
    
    private void addProject(int projectId, String projectName, LocalDate startDate, LocalDate endDate)
    {
    	
    }
    
    private void getAllProjects()
    {
    }

    private void getProjectById(int projectId)
    {
    }

    private void getAllActiveProjects()
    {
    	// Active project criteria
    	// 1 - the projects end date is after today
    	// 2 - the project has no end date
    	// DON'T worry about the start date
    }

    private void updateProject(int projectId, String projectName, LocalDate startDate, LocalDate endDate)
    {
    	
    }

    private void deleteProject(int projectId)
    {
    }


}
