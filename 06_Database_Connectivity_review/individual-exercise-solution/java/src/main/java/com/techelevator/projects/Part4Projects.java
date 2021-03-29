package com.techelevator.projects;

import java.sql.Date;
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
    	addProject(1, "Project X", LocalDate.parse("1962-03-01"), LocalDate.parse("2003-08-31"));
    	addProject(2, "Forelorn Cupcake", null, null);
    	addProject(3, "The Never-ending Project", LocalDate.parse("2011-09-01"), null);
    	addProject(4, "Absolutely Done By", null, LocalDate.parse("2021-06-30"));
    	addProject(5, "Royal Shakespeare", LocalDate.parse("2016-10-15"), LocalDate.parse("2017-03-14"));
    	addProject(6, "Plan 9", LocalDate.parse("2015-10-01"), LocalDate.parse("2021-12-31"));
    	addProject(7, "Project: Search", LocalDate.parse("2019-08-19"), null);
    	addProject(8, "The Perfect UX", LocalDate.parse("2020-04-07"), LocalDate.parse("2021-09-23"));
    	
        
                
        // 4.2 - select all projects from the database
        System.out.println("\n*** 4.2 Get all projects ***\n");
        getAllProjects();
        
        
        // 4.3 - find a project by id
        System.out.println("\n*** 4.3 Get project by Id ***\n");
        //TODO: search for project by id - try several ids - even some that don't exist
        getProjectById(0);
        getProjectById(1);
        getProjectById(5);
        getProjectById(8);
        getProjectById(9);
        
        
        // 4.4 - search for active projects
        System.out.println("\n*** 4.4 Search for active projects ***\n");
        // TODO: get active projects
        getAllActiveProjects();
        
        
        // 4.5
        System.out.println("\n*** 4.5 Change project data (UPDATE)***\n");
        //TODO: try changing the name, startDate or any other bit of project information
        // added end date to  "Project: Search"
        updateProject(7, "Project: Search", LocalDate.parse("2019-08-19"), LocalDate.parse("2021-12-20"));
        //TODO: verify that the update worked
        getProjectById(7);      

        
        // 4.6
        System.out.println("\n*** 4.6 Delete a project***\n");
        deleteProject(8);
        //verify that the delete worked
        getProjectById(8);  

    }
    
    private void addProject(int projectId, String projectName, LocalDate startDate, LocalDate endDate)
    {
    	try
		{
			String sql = "INSERT INTO project "
					+ "( "
					+ "		project_id "
					+ "		, name "
					+ "		, from_date "
					+ "		, to_date "
					+ ") "
					+ "VALUES (?, ?, ?, ?); ";
			
			jdbcTemplate.update(sql, projectId, projectName, startDate, endDate);
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error inserting project: " + projectId);
			System.err.println(e.getMessage());
			System.out.println();
		}
    	
    }
    
    private void getAllProjects()
    {
    	try
		{
			String sql = "SELECT project_id "
					+ "		, name "
					+ "		, from_date "
					+ "		, to_date "
					+ "FROM project; ";
			
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
			System.err.println("There was an error getting all projects");
			System.err.println(e.getMessage());
			System.out.println();
		}
    }

    private void getProjectById(int projectId)
    {
    	try
		{
			String sql = "SELECT project_id "
					+ "		, name "
					+ "		, from_date "
					+ "		, to_date "
					+ "FROM project "
					+ "WHERE project_id = ?; ";
			
			SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, projectId);
			
			if(rows.next())
			{
				// Date is a dataType in java.sql.Date
				Date dbStartDate = rows.getDate("from_date");
				Date dbEndDate = rows.getDate("to_date");
				
				String name = rows.getString("name");
				LocalDate startDate = (dbStartDate == null) 
										? null 
										: dbStartDate.toLocalDate();
				LocalDate endDate = (dbEndDate == null) 
										? null 
										: dbEndDate.toLocalDate();
				
				System.out.println(projectId + ": " + name + "  " + startDate + "  -  " + endDate);
			}
			else 
			{
				System.out.println("There is no project with id: " + projectId);
			}
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error getting all projects");
			System.err.println(e.getMessage());
			System.out.println();
		}
    }

    private void getAllActiveProjects()
    {
    	// Active project criteria
    	// 1 - the projects end date is after today
    	// 2 - the project has no end date
    	// DON'T worry about the start date

    	try
		{
			String sql = "SELECT project_id "
					+ "		, name "
					+ "		, from_date "
					+ "		, to_date "
					+ "FROM project "
					+ "WHERE to_date > ? "
					+ "		OR to_date IS NULL; ";
			
			SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, LocalDate.now());
			
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
			System.err.println("There was an error getting all projects");
			System.err.println(e.getMessage());
			System.out.println();
		}
    }

    private void updateProject(int projectId, String projectName, LocalDate startDate, LocalDate endDate)
    {
    	try
		{
			String sql = "UPDATE project "
					+ "SET name = ? "
					+ "		, from_date = ? "
					+ "		, to_date = ? "
					+ "WHERE project_id = ?; ";
			
			jdbcTemplate.update(sql, projectName, startDate, endDate, projectId);
			
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error updating project: " + projectId);
			System.err.println(e.getMessage());
			System.out.println();
		}
    }

    private void deleteProject(int projectId)
    {
    	try
		{
			String sql = "DELETE FROM project WHERE project_id = ?; ";
			
			jdbcTemplate.update(sql, projectId);
			
		} 
    	catch (Exception e)
		{
			System.err.println("There was an error deleting project: " + projectId);
			System.err.println(e.getMessage());
			System.out.println();
		}
    }


}
