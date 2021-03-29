using Microsoft.VisualStudio.TestTools.UnitTesting;
using ProjectOrganizer.DAL;
using ProjectOrganizer.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace ProjectOrganizer.Tests.DAL
{
    [TestClass]
    public class ProjectSqlDAOTests : ProjectOrganizerDAOTests
    {
        [TestMethod]
        public void GetAllProjects_Should_Return_The_Correct_Count()
        {
            ProjectSqlDAO dao = new ProjectSqlDAO(ConnectionString);
            IList<Project> projects = dao.GetAllProjects();
            Assert.AreEqual(1, projects.Count);
        }

        [TestMethod]
        public void CreateProject_Should_Add_Row()
        {
            ProjectSqlDAO dao = new ProjectSqlDAO(ConnectionString);
            int initialRowCount = GetRowCount("project");

            int newId = dao.CreateProject(new Project() { Name = "TEST PROJECT 2", StartDate = DateTime.Now, EndDate = DateTime.Now.AddDays(1) });

            Assert.AreEqual(ProjectId + 1, newId);
            Assert.AreEqual(initialRowCount + 1, GetRowCount("project"));
        }

        [TestMethod]
        public void AssignEmployeeToProject_Should_Add_Row()
        {
            ProjectSqlDAO dao = new ProjectSqlDAO(ConnectionString);
            int initialRowCount = GetRowCount("project_employee");

            bool added = dao.AssignEmployeeToProject(ProjectId, UnassignedEmployeeId);

            Assert.IsTrue(added);
            Assert.AreEqual(initialRowCount + 1, GetRowCount("project_employee"));
        }

        [TestMethod]
        public void RemoveEmployeeFromProject_Should_Remove_Row()
        {
            ProjectSqlDAO dao = new ProjectSqlDAO(ConnectionString);
            int initialRowCount = GetRowCount("project_employee");

            bool removed = dao.RemoveEmployeeFromProject(ProjectId, AssignedEmployeeId);

            Assert.IsTrue(removed);
            Assert.AreEqual(initialRowCount - 1, GetRowCount("project_employee"));

        }
    }
}
