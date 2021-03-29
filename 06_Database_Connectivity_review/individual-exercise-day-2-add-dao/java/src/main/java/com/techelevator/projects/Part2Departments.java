package com.techelevator.projects;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.projects.models.Department;
import com.techelevator.projects.models.dao.DepartmentDAO;
import com.techelevator.projects.models.jdbc.JDBCDepartmentDAO;

public class Part2Departments
{
    DepartmentDAO dao = new JDBCDepartmentDAO();

    public void run()
    {
    	// *********************************
        // Part 2 - Working with departments
    	// *********************************
    	
    	// 2.1 - add all departments to the database
    	System.out.println("*** 2.1 Adding departments ***\n");
        addDepartment(1, "Department of Redundancey Department"); // misspelled on purpose
        addDepartment(2, "Network Administration");      
        addDepartment(3, "Research and Development");      
        addDepartment(4, "Store Support");        
        // TODO: add the remaining departments to the database        
        // TODO: add some of your own deparments - any name will do :)
        addDepartment(5, "Software Engineering");
        addDepartment(82, "Team Party Planners");
        
        
        // 2.2 - select all departments from the database
        System.out.println("\n*** 2.2 Displaying departments from the database ***\n");
        getAllDepartments();
        
        
        // 2.3 - find a department by id
        System.out.println("\n*** 2.3 Get department by Id ***\n");
        getDepartmentById(1);
        getDepartmentById(18);
        getDepartmentById(82);
        // try searching for some of your own
        getDepartmentById(2);
        getDepartmentById(3);
        getDepartmentById(4);
        getDepartmentById(5);//edge test
        getDepartmentById(0);//edge test
        
        // 2.4 - search for department by name
        System.out.println("\n*** 2.4 Search for department by name ***\n");
        searchForDepartmentByName("red");
        searchForDepartmentByName("Redundant");
        // try some other name searches
        searchForDepartmentByName("Network");
        searchForDepartmentByName("network");
        searchForDepartmentByName("NETWORK");
        searchForDepartmentByName("work");
        
        
        // 2.5
        System.out.println("\n*** 2.5 Modify a department name (UPDATE)***\n");
        updateDepartment(1, "Department of Redundancy Department");
        //verify that the update worked
        getDepartmentById(1);       

        
        // 2.6
        System.out.println("\n*** 2.6 Delete the Party department***\n");
        deleteDepartment(82);
        //verify that the delete worked
        getDepartmentById(82);

    	
    }

    private void addDepartment(int id, String name)
    {
    	Department department = new Department(id, name);
    	try
		{				
	        dao.addDepartment(department);
		} 
    	catch (Exception e)
		{
    		System.err.println("There was an error adding the following department --> " + id + ": " + name);
		}

    }
    
    private void getAllDepartments()
    {
    	List<Department> departments = dao.getAllDepartments();
        
        for (Department department : departments)
		{        	
        	System.out.println(department.getId() + ": " + department.getName());
        }
    }

    private void getDepartmentById(int id)
    {
    	Department department = dao.getDepartment(id);
       
        if(department != null)
        {
        	System.out.println(department.getId() + ": " + department.getName());
        }
        else 
        {
			System.out.println("There is no department with id: " + id);
		}
    }

    private void searchForDepartmentByName(String name)
    {
    	List<Department> departments = dao.searchByName(name);
        
        for (Department department : departments)
		{        	
        	System.out.println(department.getId() + ": " + department.getName());
        }
    }
    
    private void updateDepartment(int id, String name)
    {
    	try
		{				
	        Department department = new Department(id, name);
	        dao.updateDepartment(id, department);
		} 
    	catch (Exception e)
		{
    		System.err.println("There was an error updating the following department --> " + id);
		}

    }

    private void deleteDepartment(int id)
    {
    	try
		{				
	        dao.deleteDepartment(id);
		} 
    	catch (Exception e)
		{
    		System.err.println("There was an error deleting department --> " + id);
		}
    }
    
  
}
