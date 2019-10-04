package com.cg.pcm;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class ProjectCodeModuleApplication {

	// main method of the project
	public static void main(String[] args) {
		SpringApplication.run(ProjectCodeModuleApplication.class, args);
		System.out.println("Welcome to Project Code Module");
		Logger log = LogManager.getLogger(ProjectCodeModuleApplication.class);
		log.info("something");
		
	}

}
