package com.techelevator.projects.models.dao;

import java.util.List;

import com.techelevator.projects.models.Project;

public interface ProjectDAO
{
	Project getProject(int id);
	List<Project> getAllProjects();
	List<Project> getActiveProjects();
	Project addProject(Project project);
	void updateProject(int id, Project project);
	void deleteProject(int id);
}
