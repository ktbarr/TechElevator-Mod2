package com.techelevator.projects.models.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.models.Project;
import com.techelevator.projects.models.dao.ProjectDAO;

public class JDBCProjectDAO implements ProjectDAO
{
    JdbcTemplate jdbcTemplate;

    public JDBCProjectDAO()
    {
        BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public Project getProject(int id)
	{
		Project project = null;
		
		String sql = "SELECT project_id "
				+ "		, name "
				+ "		, from_date "
				+ "		, to_date "
				+ "FROM project "
				+ "WHERE project_id = ?; ";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);
		
		if(rows.next())
		{
			project = mapRowToProject(rows);
		}
		
		return project;
	}

	@Override
	public List<Project> getAllProjects()
	{
		List<Project> projects = new ArrayList<Project>();
		
		String sql = "SELECT project_id "
				+ "		, name "
				+ "		, from_date "
				+ "		, to_date "
				+ "FROM project; ";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		while(rows.next())
		{
			Project project = mapRowToProject(rows);
			projects.add(project);
		}
		
		return projects;
	}

	@Override
	public List<Project> getActiveProjects()
	{
		List<Project> projects = new ArrayList<Project>();
		
		String sql = "SELECT project_id "
				+ "		, name "
				+ "		, from_date "
				+ "		, to_date "
				+ "FROM project "
				+ "WHERE to_date > ? "
				+ "		OR to_date IS NULL; ";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		
		while(rows.next())
		{
			Project project = mapRowToProject(rows);
			projects.add(project);
		}
		
		return projects;
	}

	@Override
	public Project addProject(Project project)
	{

		String sql = "INSERT INTO project "
				+ "( "
				+ "		project_id "
				+ "		, name "
				+ "		, from_date "
				+ "		, to_date "
				+ ") "
				+ "VALUES (?, ?, ?, ?); ";
		
		jdbcTemplate.update(sql
				, project.getId()
				, project.getName()
				, project.getStartDate()
				, project.getEndDate());
		
		return project;
	}

	@Override
	public void updateProject(int id, Project project)
	{
		String sql = "UPDATE project "
				+ "SET name = ? "
				+ "		, from_date = ? "
				+ "		, to_date = ? "
				+ "WHERE project_id = ?; ";
		
		jdbcTemplate.update(sql
				, project.getName()
				, project.getStartDate()
				, project.getEndDate()
				, id);
		
	}

	@Override
	public void deleteProject(int id)
	{
		String sql = "DELETE FROM project WHERE project_id = ?; ";
		
		jdbcTemplate.update(sql, id);		
	}

	public static Project mapRowToProject(SqlRowSet row)
	{
		// Date is a dataType in java.sql.Date
		Date dbStartDate = row.getDate("from_date");
		Date dbEndDate = row.getDate("to_date");
		
		int id = row.getInt("project_id");
		String name = row.getString("name");
		LocalDate startDate = (dbStartDate == null) 
								? null 
								: dbStartDate.toLocalDate();
		LocalDate endDate = (dbEndDate == null) 
								? null 
								: dbEndDate.toLocalDate();
	
		return new Project(id, name, startDate, endDate);
	}
}
