using Microsoft.VisualStudio.TestTools.UnitTesting;
using ProjectOrganizer.DAL;
using ProjectOrganizer.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace ProjectOrganizer.Tests.DAL
{
    [TestClass]
    public class EmployeeSqlDAOTests : ProjectOrganizerDAOTests
    {
        [DataTestMethod]
        [DataRow(null, null, 0)]
        [DataRow("", "", 0)]
        [DataRow("", "doesn't matter", 0)]
        [DataRow("doestn't matter", "", 0)]
        [DataRow("doesn't matter", "doesn't matter", 0)]
        [DataRow("Emp", "doesn't matter", 2)]
        public void EmployeeSearch_Should_Return_Correct_Results(string firstName, string lastName, int expectedCount)
        {
            EmployeeSqlDAO dao = new EmployeeSqlDAO(ConnectionString);
            IList<Employee> employees = dao.Search(firstName, lastName);
            Assert.AreEqual(expectedCount, employees.Count);
        }

        [TestMethod]
        public void GetAllEmployees_Should_Return_Correct_Count()
        {
            EmployeeSqlDAO dao = new EmployeeSqlDAO(ConnectionString);
            IList<Employee> employees = dao.GetAllEmployees();
            Assert.AreEqual(2, employees.Count);
        }

        [TestMethod]
        public void GetEmployeesWithoutProjects_Should_Return_Correct_Count()
        {
            EmployeeSqlDAO dao = new EmployeeSqlDAO(ConnectionString);
            IList<Employee> employees = dao.GetEmployeesWithoutProjects();
            Assert.AreEqual(1, employees.Count);
            Assert.AreEqual(UnassignedEmployeeId, employees[0].EmployeeId);
        }
    }
}
