package com.cg.pcm.controller;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.pcm.ProjectCodeModuleApplication;
import com.cg.pcm.entity.Project;
import com.cg.pcm.exception.ProjectException;
import com.cg.pcm.service.ProjectService;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:4200")
public class ProjectController {

	@Autowired
	ProjectService service;

	Logger log = LogManager.getLogger(ProjectController.class);

	/**
	 * This module return the object of Project that is searched.
	 * 
	 * @param id Project ID of Long Type
	 * 
	 * @return  Object of the project type according to project ID
	 */
	@GetMapping(path = "/{id}")
	public Project getProjectById(@PathVariable("id") long id) throws ProjectException {
		log.info("displaying project by given Id...");
		return service.getProjectById(id);
	}

	/**
	 * This module deletes the existing project details and returns the deleted
	 * project object.
	 * 
	 * @param id Project ID of Long Type
	 * 
	 * @return  Object of the project type which is deleted
	 */
	@DeleteMapping(path = "/{id}")
	public Project deleteProjectById(@PathVariable("id") long id) throws ProjectException {
		log.info("Project Deleted By Given Id...");
		return service.deleteProjectById(id);
	}

	/**
	 * This module return the ArrayList of project contained in the database.
	 * 
	 * @return  Array List of object of the project type.
	 */
	@GetMapping(path = "/")
	public ArrayList<Project> getAllProjects() throws ProjectException {
		log.info("Fetching all Projects... ");
		return service.getAllProjects();
	}

	/**
	 * This module adds new project in the database and returns that object.
	 * 
	 * @param project Object of Project type
	 * @return  Array List of object of the Project type.
	 */
	@PostMapping(path = "/")
	public Project addProject(@RequestBody Project project) throws ProjectException {
		if (service.validateProjectDescription(project) && service.validateStartAndEndDate(project)) {
			log.info("Project Added");
			return service.addProject(project);
		}
		log.warn("Project Not Added");
		return null;
	}

	/**
	 * This module updates the existing project details and return the updated
	 * object.
	 * 
	 * @param project Object of Project type to be added
	 * @return Object of Project type that is updated.
	 */
	@PutMapping(path = "/")
	public Project updateProject(@RequestBody Project project) throws ProjectException {
		if (service.validateProjectDescription(project) && service.validateStartAndEndDate(project)) {
			log.info("Project Updated Succesfully...");
			return service.updateProject(project);
		}
		log.warn("Project Not Updated...");
		return null;
	}
}
