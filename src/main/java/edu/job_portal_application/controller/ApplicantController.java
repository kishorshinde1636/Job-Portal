package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.dto.ApplicantDto;
import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.service.ApplicantService;
import edu.job_portal_application.util.ResponseStructure;

@RequestMapping("/applicant")
@RestController
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@PostMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> addApplicant(@RequestBody Applicant applicant) {
		return applicantService.addApplicant(applicant);
	}
}
