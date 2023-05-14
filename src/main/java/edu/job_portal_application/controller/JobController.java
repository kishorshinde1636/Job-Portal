package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.dto.JobDto;
import edu.job_portal_application.entity.Job;
import edu.job_portal_application.service.JobService;
import edu.job_portal_application.util.ResponseStructure;

@RestController
@RequestMapping("/job")
public class JobController {

	@Autowired
	private JobService jobService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Job>> addJob(@RequestBody JobDto dto, @RequestParam long employerId) {
		return jobService.addJob(dto, employerId);
	}

}
