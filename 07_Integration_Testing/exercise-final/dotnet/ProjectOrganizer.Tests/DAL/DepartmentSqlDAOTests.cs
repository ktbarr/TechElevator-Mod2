using Microsoft.VisualStudio.TestTools.UnitTesting;
using ProjectOrganizer.DAL;
using ProjectOrganizer.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace ProjectOrganizer.Tests.DAL
{
    [TestClass]
    public class DepartmentSqlDAOTests : ProjectOrganizerDAOTests
    {
        [TestMethod]
        public void GetDepartments_Should_Return_All_Departments()
        {
            DepartmentSqlDAO dao = new DepartmentSqlDAO(ConnectionString);
            IList<Department> departments = dao.GetDepartments();
            Assert.AreEqual(1, departments.Count);
        }

        [TestMethod]
        public void CreateDepartment_Should_Return_New_Id()
        {
            Department department = new Department()
            {
                Name = "TEST DEPARTMENT NAME"
            };
            int initialRowCount = GetRowCount("department");
            DepartmentSqlDAO dao = new DepartmentSqlDAO(ConnectionString);

            int newId = dao.CreateDepartment(department);

            Assert.AreEqual(DepartmentId + 1, newId);
            Assert.AreEqual(initialRowCount + 1, GetRowCount("department"));
        }

        [TestMethod]
        public void UpdateDepartment_Should_Return_True()
        {
            Department department = new Department()
            {
                Id = DepartmentId,
                Name = "UPDATED DEPT NAME"
            };
            int initialRowCount = GetRowCount("department");
            DepartmentSqlDAO dao = new DepartmentSqlDAO(ConnectionString);

            bool result = dao.UpdateDepartment(department);

            Assert.IsTrue(result);
            Assert.AreEqual(initialRowCount, GetRowCount("department"));
        }

    }
}
