using ProjectOrganizer.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectOrganizer.DAL
{
    public class EmployeeSqlDAO : IEmployeeDAO
    {
        private string connectionString;

        // Single Parameter Constructor
        public EmployeeSqlDAO(string dbConnectionString)
        {
            connectionString = dbConnectionString;
        }

        /// <summary>
        /// Returns a list of all of the employees.
        /// </summary>
        /// <returns>A list of all employees.</returns>
        public IList<Employee> GetAllEmployees()
        {
            List<Employee> employees = new List<Employee>();

            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    SqlCommand cmd = new SqlCommand("SELECT * FROM employee;", conn);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Employee employee = CreateEmployeeFromReader(reader);
                        employees.Add(employee);
                    }
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("Error getting employees.");
                Console.WriteLine(ex.Message);
                throw;
            }

            return employees;
        }

        /// <summary>
        /// Searches the system for an employee by first name or last name.
        /// </summary>
        /// <remarks>The search performed is a wildcard search.</remarks>
        /// <param name="firstname"></param>
        /// <param name="lastname"></param>
        /// <returns>A list of employees that match the search.</returns>
        public IList<Employee> Search(string firstname, string lastname)
        {
            List<Employee> employees = new List<Employee>();

            // If I have none then return back an empty set
            if (String.IsNullOrEmpty(firstname) && String.IsNullOrEmpty(lastname))
            {
                // return an empty list
                return employees;
            }

            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();

                    string sql = @"SELECT * FROM employee WHERE ";
                    SqlCommand cmd = new SqlCommand();

                    if (!String.IsNullOrEmpty(firstname))
                    {
                        sql += "first_name LIKE @first_name";
                        cmd.Parameters.AddWithValue("@first_name", "%" + firstname + "%");
                    }

                    if (!String.IsNullOrEmpty(firstname) && !String.IsNullOrEmpty(lastname))
                    {
                        sql += " OR ";
                    }

                    if (!String.IsNullOrEmpty(lastname))
                    {
                        sql += "last_name LIKE @last_name";
                        cmd.Parameters.AddWithValue("@last_name", "%" + lastname + "%");
                    }

                    cmd.CommandText = sql;
                    cmd.Connection = conn;
                    
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Employee employee = CreateEmployeeFromReader(reader);
                        employees.Add(employee);
                    }
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("Error searching for employees.");
                Console.WriteLine(ex.Message);
                throw;
            }

            return employees;
        }



        /// <summary>
        /// Gets a list of employees who are not assigned to any active projects.
        /// </summary>
        /// <returns></returns>
        public IList<Employee> GetEmployeesWithoutProjects()
        {
            List<Employee> employees = new List<Employee>();

            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    SqlCommand cmd = new SqlCommand(@"SELECT * FROM employee WHERE employee_id NOT IN 
                        (select employee_id from project_employee 
                        join project on project_employee.project_id = project.project_id 
                        WHERE project.from_date <= CONVERT(date, GETDATE()) AND CONVERT(date,GETDATE()) <= project.to_date)", conn);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Employee employee = CreateEmployeeFromReader(reader);
                        employees.Add(employee);
                    }
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("Error getting employees without projects.");
                Console.WriteLine(ex.Message);
                throw;
            }

            return employees;
        }

        private Employee CreateEmployeeFromReader(SqlDataReader reader)
        {
            Employee employee = new Employee();

            employee.EmployeeId = Convert.ToInt32(reader["employee_id"]);
            employee.DepartmentId = Convert.ToInt32(reader["department_id"]);
            employee.FirstName = Convert.ToString(reader["first_name"]);
            employee.LastName = Convert.ToString(reader["last_name"]);
            employee.JobTitle = Convert.ToString(reader["job_title"]);
            employee.BirthDate = Convert.ToDateTime(reader["birth_date"]);
            employee.Gender = Convert.ToString(reader["gender"]);
            employee.HireDate = Convert.ToDateTime(reader["hire_date"]);

            return employee;
        }
    }
}
