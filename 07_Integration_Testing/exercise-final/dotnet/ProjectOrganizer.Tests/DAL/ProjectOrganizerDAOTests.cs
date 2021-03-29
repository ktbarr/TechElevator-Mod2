using Microsoft.VisualStudio.TestTools.UnitTesting;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.IO;
using System.Text;
using System.Transactions;

namespace ProjectOrganizer.Tests.DAL
{
    [TestClass]
    public class ProjectOrganizerDAOTests
    {
        /// <summary>
        /// The connection string used for the integration tests.
        /// </summary>
        protected string ConnectionString { get; } = @"Data Source=.\SQLEXPRESS;Initial Catalog=EmployeeDB;Integrated Security=True";

        /// <summary>
        /// The department id created for the test.
        /// </summary>
        protected int DepartmentId { get; private set; }

        /// <summary>
        /// The employee id created for the test.
        /// </summary>
        protected int AssignedEmployeeId { get; private set; }

        /// <summary>
        /// The employee id (unassigned to a project) for the test.
        /// </summary>
        protected int UnassignedEmployeeId { get; private set; }

        /// <summary>
        /// The project id created for the test.
        /// </summary>
        protected int ProjectId { get; private set; }

        /// <summary>
        /// The transaction used for each test.
        /// </summary>
        private TransactionScope transaction;

        /// <summary>
        /// Initializes the database for the test.
        /// </summary>
        [TestInitialize]
        public void Setup()
        {
            // Begin the transaction
            transaction = new TransactionScope();

            // Get the sql
            string sql = File.ReadAllText("test-data.sql");

            using (SqlConnection conn = new SqlConnection(ConnectionString))
            {
                conn.Open();
                SqlCommand cmd = new SqlCommand(sql, conn);
                SqlDataReader reader = cmd.ExecuteReader();

                if (reader.Read())
                {
                    DepartmentId = Convert.ToInt32(reader["departmentId"]);
                    AssignedEmployeeId = Convert.ToInt32(reader["assignedEmployeeId"]);
                    UnassignedEmployeeId = Convert.ToInt32(reader["unassignedEmployeeId"]);
                    ProjectId = Convert.ToInt32(reader["projectId"]);
                }
            }
        }

        /// <summary>
        /// Cleans up the database after each test.
        /// </summary>
        [TestCleanup]
        public void Cleanup()
        {
            transaction.Dispose();
        }

        /// <summary>
        /// Gets the row count for a given table.
        /// </summary>
        /// <param name="table"></param>
        /// <returns></returns>
        protected int GetRowCount(string table)
        {
            using (SqlConnection conn = new SqlConnection(ConnectionString))
            {
                conn.Open();
                SqlCommand cmd = new SqlCommand($"SELECT COUNT(*) FROM {table};", conn);
                int count = Convert.ToInt32(cmd.ExecuteScalar());
                return count;
            }
        }
    }
}
