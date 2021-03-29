package com.techelevator.projects.models;

import java.time.LocalDate;

public class Employee
{
	private int id;
	private int departmentId;
	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate birthDate;
	private LocalDate hireDate;
	
	public Employee()
	{
	}

	public Employee(int id, int departmentId, String firstName, String lastName, String gender, LocalDate birthDate, LocalDate hireDate)
	{
		this.id = id;
		this.departmentId = departmentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getDepartmentId()
	{
		return departmentId;
	}

	public void setDepartmentId(int departmentId)
	{
		this.departmentId = departmentId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public LocalDate getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate = birthDate;
	}

	public LocalDate getHireDate()
	{
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate)
	{
		this.hireDate = hireDate;
	}
	
}
