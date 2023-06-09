package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.entity.JobApplication;
import edu.job_portal_application.service.JobApplicationService;
import edu.job_portal_application.util.ResponseStructure;

@RestController
@RequestMapping("/jobApplication")
public class JobApplicationController {

	@Autowired
	private JobApplicationService applicationService;

	@PostMapping
	public ResponseEntity<ResponseStructure<JobApplication>> saveJobApplication(@RequestParam int applicantId,
			@RequestParam long jobId) {
		return applicationService.saveJobApplication(applicantId, jobId);
	}
}
