using ProjectOrganizer.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectOrganizer.DAL
{
    public class DepartmentSqlDAO : IDepartmentDAO
    {
        private string connectionString;

        // Single Parameter Constructor
        public DepartmentSqlDAO(string dbConnectionString)
        {
            connectionString = dbConnectionString;
        }

        /// <summary>
        /// Returns a list of all of the departments.
        /// </summary>
        /// <returns></returns>
        public IList<Department> GetDepartments()
        {
            List<Department> departments = new List<Department>();

            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();

                    SqlCommand cmd = new SqlCommand("SELECT * FROM department", conn);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        departments.Add(CreateDepartmentFromReader(reader));
                    }
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("Error reading departments.");
                Console.WriteLine(ex.Message);
                throw;
            }

            return departments;
        }

        private Department CreateDepartmentFromReader(SqlDataReader reader)
        {
            Department department = new Department();
            department.Id = Convert.ToInt32(reader["department_id"]);
            department.Name = Convert.ToString(reader["name"]);

            return department;
        }

        /// <summary>
        /// Creates a new department.
        /// </summary>
        /// <param name="newDepartment">The department object.</param>
        /// <returns>The id of the new department (if successful).</returns>
        public int CreateDepartment(Department newDepartment)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    SqlCommand cmd = new SqlCommand("INSERT INTO department VALUES (@name); SELECT @@IDENTITY;", conn);
                    cmd.Parameters.AddWithValue("@name", newDepartment.Name);

                    int id = Convert.ToInt32(cmd.ExecuteScalar());

                    return id;
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("Error creating a new department");
                Console.WriteLine(ex.Message);
                throw;
            }
        }
        
        /// <summary>
        /// Updates an existing department.
        /// </summary>
        /// <param name="updatedDepartment">The department object.</param>
        /// <returns>True, if successful.</returns>
        public bool UpdateDepartment(Department updatedDepartment)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    SqlCommand cmd = new SqlCommand("UPDATE department SET name = @name WHERE department_id = @department_id;", conn);
                    cmd.Parameters.AddWithValue("@name", updatedDepartment.Name);
                    cmd.Parameters.AddWithValue("@department_id", updatedDepartment.Id);

                    int affectedRows = cmd.ExecuteNonQuery();

                    return affectedRows > 0;
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("Error creating a new department");
                Console.WriteLine(ex.Message);
                throw;
            }
        }

    }
}
