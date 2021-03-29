package com.techelevator.projects.models.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.models.Employee;
import com.techelevator.projects.models.dao.EmployeeDAO;

public class JDBCEmployeeDAO implements EmployeeDAO
{
    JdbcTemplate jdbcTemplate;

    public JDBCEmployeeDAO()
    {
        BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/projects");
		dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

	@Override
	public Employee getEmployee(int id)
	{
		Employee employee = null;

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
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql, id);
		
		// loop through the rows
		if(rows.next())
		{
			employee = mapRowToEmployee(rows);			
		}
		
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees()
	{
		List<Employee> employees = new ArrayList<Employee>();
		
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
			Employee employee = mapRowToEmployee(rows);
			
			employees.add(employee);			
		}
		
		return employees;
	}

	@Override
	public List<Employee> searchByName(String firstName, String lastName)
	{
		List<Employee> employees = new ArrayList<Employee>();
		
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
			Employee employee = mapRowToEmployee(rows);
			
			employees.add(employee);			
		}
		
		return employees;
	}

	@Override
	public Employee addEmployee(Employee employee)
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
				, employee.getId()
				, employee.getDepartmentId()
				, employee.getFirstName()
				, employee.getLastName()
				, employee.getBirthDate()
				, employee.getGender()
				, employee.getHireDate()); 
		
		return employee;
	}

	@Override
	public void updateEmployee(int id, Employee employee)
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
				, employee.getDepartmentId()
				, employee.getFirstName()
				, employee.getLastName()
				, employee.getBirthDate()
				, employee.getGender()
				, employee.getHireDate()
				, id);
		
	}

	@Override
	public void deleteEmployee(int id)
	{
		String sql = "DELETE FROM employee WHERE employee_id = ?; ";
		
		// use jdbcTemplate to execute the query - it returns a SqlRowSet
		jdbcTemplate.update(sql, id);
		
	}
	
	public static Employee mapRowToEmployee(SqlRowSet row)
	{
		int id = row.getInt("employee_id");
		Integer departmentId = row.getInt("department_id"); //Integer because department could be null
		String firstName = row.getString("first_name");
		String lastName = row.getString("last_name");
		LocalDate birthDate = row.getDate("birth_date").toLocalDate();
		String gender = row.getString("gender");
		LocalDate hireDate = row.getDate("hire_date").toLocalDate();
		
		return new Employee(id, departmentId, firstName, lastName, gender, birthDate, hireDate);
	}

}
