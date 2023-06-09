package edu.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.job_portal_application.entity.Project;
import edu.job_portal_application.repo.ProjectRepo;

@Repository
public class ProjectDao {

	@Autowired
	private ProjectRepo projectRepo;

	public Project addProject(Project project) {
		return projectRepo.save(project);
	}

	public Optional<Project> getProjectById(int applicantId) {
		return projectRepo.findById(applicantId);
	

	}

	public void deleteProject(Project project) {

		projectRepo.delete(project);
	}

}
