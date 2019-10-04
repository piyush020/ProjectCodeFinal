package com.cg.pcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.pcm.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	
}
