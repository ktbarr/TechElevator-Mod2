package com.techelevator.projects.models.jdbc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.models.Employee;
import com.techelevator.projects.models.Project;
import com.techelevator.projects.models.dao.ProjectEmployeeDAO;

public class JDBCProjectEmployeeDAO implements ProjectEmployeeDAO
{
    JdbcTemplate jdbcTemplate;

    public JDBCProjectEmployeeDAO()
    {
        BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
	@Override
	public void assignEmployeeToProject(int projectId, int employeeId)
	{
		String sql = "INSERT INTO project_employee "
				+ "( "
				+ "		project_id "
				+ "		, employee_id "
				+ ") "
				+ "VALUES (?, ?); ";
		
		jdbcTemplate.update(sql, projectId, employeeId);
		
	}

	@Override
	public void removeEmployeeFromProject(int projectId, int employeeId)
	{
		String sql = "DELETE FROM project_employee "
				+ "WHERE project_id = ? "
				+ "		AND employee_id = ?; ";
		
		jdbcTemplate.update(sql, projectId, employeeId);
		
	}

	@Override
	public List<Employee> getEmployeesByProject(int projectId)
	{

		List<Employee> employees = new ArrayList<Employee>();
		
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
			// I can reuse the mapRowToEmployee function in the JDBCEmployeeDAO class
			Employee employee = JDBCEmployeeDAO.mapRowToEmployee(rows);
			employees.add(employee);			
		}
		
		return employees;
	}

	@Override
	public List<Project> getProjectsByEmployee(int employeeId)
	{
		List<Project> projects = new ArrayList<Project>();
		
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
			Project project = JDBCProjectDAO.mapRowToProject(rows);
			projects.add(project);
		}
		
		return projects;
	}

	@Override
	public List<Employee> getEmployeesWithoutProjects()
	{
		List<Employee> employees = new ArrayList<Employee>();
		
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
			// I can reuse the mapRowToEmployee function in the JDBCEmployeeDAO class
			Employee employee = JDBCEmployeeDAO.mapRowToEmployee(rows);
			employees.add(employee);			
		}
		
		return employees;
	}

	@Override
	public List<Project> getProjectsWithoutEmployees()
	{
		List<Project> projects = new ArrayList<Project>();
		
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
			Project project = JDBCProjectDAO.mapRowToProject(rows);
			projects.add(project);
		}
		
		return projects;
	}

}
