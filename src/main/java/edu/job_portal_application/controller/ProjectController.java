package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.dto.ProjectDto;
import edu.job_portal_application.entity.Resume;
import edu.job_portal_application.service.ProjectService;
import edu.job_portal_application.util.ResponseStructure;

@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> addProject(@RequestParam int applicantId,
			@RequestBody ProjectDto projectDto) {
		return projectService.addProject(applicantId, projectDto);
	}

}
