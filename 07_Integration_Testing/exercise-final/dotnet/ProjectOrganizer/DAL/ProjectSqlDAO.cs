using ProjectOrganizer.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ProjectOrganizer.DAL
{
    public class ProjectSqlDAO : IProjectDAO
    {
        private string connectionString;

        // Single Parameter Constructor
        public ProjectSqlDAO(string dbConnectionString)
        {
            connectionString = dbConnectionString;
        }

        /// <summary>
        /// Returns all projects.
        /// </summary>
        /// <returns></returns>
        public IList<Project> GetAllProjects()
        {
            List<Project> projects = new List<Project>();

            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    SqlCommand cmd = new SqlCommand("SELECT * FROM project", conn);
                    SqlDataReader reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Project project = CreateProjectFromReader(reader);
                        projects.Add(project);
                    }
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("Error occured reading all projects");
                Console.WriteLine(ex.Message);
                throw;
            }

            return projects;
        }

        private Project CreateProjectFromReader(SqlDataReader reader)
        {
            Project project = new Project();
            project.ProjectId = Convert.ToInt32(reader["project_id"]);
            project.Name = Convert.ToString(reader["name"]);
            project.StartDate = Convert.ToDateTime(reader["from_date"]);
            project.EndDate = Convert.ToDateTime(reader["to_date"]);
            return project;
        }

        /// <summary>
        /// Assigns an employee to a project using their IDs.
        /// </summary>
        /// <param name="projectId">The project's id.</param>
        /// <param name="employeeId">The employee's id.</param>
        /// <returns>If it was successful.</returns>
        public bool AssignEmployeeToProject(int projectId, int employeeId)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    SqlCommand cmd = new SqlCommand("INSERT INTO project_employee VALUES (@projectId, @employeeId);", conn);
                    cmd.Parameters.AddWithValue("@projectId", projectId);
                    cmd.Parameters.AddWithValue("@employeeId", employeeId);

                    int rowsAffected = cmd.ExecuteNonQuery();
                    return rowsAffected > 0;
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("An error occurred assigning an employee to a project.");
                Console.WriteLine(ex.Message);
                throw;
            }
        }

        /// <summary>
        /// Removes an employee from a project.
        /// </summary>
        /// <param name="projectId">The project's id.</param>
        /// <param name="employeeId">The employee's id.</param>
        /// <returns>If it was successful.</returns>
        public bool RemoveEmployeeFromProject(int projectId, int employeeId)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    SqlCommand cmd = new SqlCommand("DELETE FROM project_employee WHERE project_id = @project_id AND employee_id = @employee_id;", conn);
                    cmd.Parameters.AddWithValue("@project_id", projectId);
                    cmd.Parameters.AddWithValue("@employee_id", employeeId);

                    int rowsAffected = cmd.ExecuteNonQuery();
                    return rowsAffected > 0;
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("An error occurred removing an employee from a project.");
                Console.WriteLine(ex.Message);
                throw;
            }
        }

        /// <summary>
        /// Creates a new project.
        /// </summary>
        /// <param name="newProject">The new project object.</param>
        /// <returns>The new id of the project.</returns>
        public int CreateProject(Project newProject)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    SqlCommand cmd = new SqlCommand("INSERT INTO project VALUES (@name, @from_date, @to_date); SELECT @@IDENTITY;", conn);
                    cmd.Parameters.AddWithValue("@name", newProject.Name);
                    cmd.Parameters.AddWithValue("@from_date", newProject.StartDate);
                    cmd.Parameters.AddWithValue("@to_date", newProject.EndDate);

                    int id = Convert.ToInt32(cmd.ExecuteScalar());
                    return id;
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("An error occurred creating a new project.");
                Console.WriteLine(ex.Message);
                throw;
            }
        }

    }
}
