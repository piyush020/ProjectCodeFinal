package com.cg.pcm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cg.pcm.entity.Project;
import com.cg.pcm.exception.ProjectException;

@Service
public interface ProjectService {

	public boolean validateProjectDescription(Project project) throws ProjectException;

	public boolean validateStartAndEndDate(Project project) throws ProjectException;

	public Project addProject(Project project) throws ProjectException;

	public Project updateProject(Project project) throws ProjectException;

	public Project deleteProjectById(long id) throws ProjectException;

	public ArrayList<Project> getAllProjects() throws ProjectException;

	public Project getProjectById(long id) throws ProjectException;

}
