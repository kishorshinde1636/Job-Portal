package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.dto.ResumeDto;
import edu.job_portal_application.entity.Resume;
import edu.job_portal_application.service.ResumeService;
import edu.job_portal_application.util.ResponseStructure;

@RequestMapping("/resume")
@RestController
public class ResumeController {

	@Autowired
	private ResumeService resumeService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Resume>> saveResume(@RequestBody ResumeDto resumeDto,
			@RequestParam int applicantId) {
		return resumeService.saveResume(applicantId, resumeDto);
	}

}
