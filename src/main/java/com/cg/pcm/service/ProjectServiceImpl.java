package com.cg.pcm.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.pcm.controller.ProjectController;
import com.cg.pcm.entity.Project;
import com.cg.pcm.exception.ProjectException;
import com.cg.pcm.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository repository;
	
	Logger log = LogManager.getLogger(ProjectController.class);

	@Override
	public boolean validateProjectDescription(Project project) throws ProjectException {
		String pattern="^[A-Za-z0-9_]+$";
		if(project.getDescription()!=null && Pattern.matches(pattern, project.getDescription())) {
			log.info("ProjectDescription validated Succesfully...");
			return true;
		}
		log.warn("ProjectDescription validation failed...");
		return false;
	}

	@Override
	public boolean validateStartAndEndDate(Project project) throws ProjectException {
		 if (project.getStartDate()!=null && project.getEndDate()!=null && project.getEndDate().after(project.getStartDate()))
		 {
			 log.info("Start And End Date validated Succesfully...");
			 return true;
		 }
		  log.warn("Start And End Date validation failed...");
		return false;
	}

	@Override
	public Project addProject(Project project) throws ProjectException {
		log.info("Project added Succesfully...");
		return repository.save(project);
	}

	@Override
	public Project updateProject(Project project) throws ProjectException {
		if (repository.findById(project.getId()).isPresent()) {
			log.info("Project Updated Succesfully...");
			return repository.save(project);
		}
		log.warn("Project Updation failed...");
		return null;
	}

	@Override
	public Project deleteProjectById(long id) throws ProjectException {
		Optional<Project> project = repository.findById(id);
		Project toBeReturned;
		if (project.isPresent()) {
			toBeReturned = project.get();
			repository.deleteById(id);
			log.info("Project deleted Succesfully...");
		} else {
			toBeReturned = null;
			log.info("Fetching All Projects...");
		}
		return toBeReturned;
	}

	@Override
	public ArrayList<Project> getAllProjects() throws ProjectException {
		log.info("Fetching All Projects...");
		return (ArrayList<Project>) repository.findAll();
	}

	@Override
	public Project getProjectById(long id) throws ProjectException {
		Optional<Project> project = repository.findById(id);
		if (project.isPresent()) {
			log.info("Fetching Project by given Id...");
			return project.get();
		}
		log.info("Given ID Doesn't exists...");
		return null;
	}

}
