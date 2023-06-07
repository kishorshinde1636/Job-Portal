package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.dto.ApplicantDto;
import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.service.ApplicantService;
import edu.job_portal_application.util.ResponseStructure;
import lombok.Delegate;

@RequestMapping("/applicant")
@RestController
@CrossOrigin
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@PostMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> addApplicant(@RequestBody Applicant applicant) {
		return applicantService.addApplicant(applicant);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> getApplicantById(int applicantId) {
		return applicantService.getApplicantById(applicantId);
	}

	@DeleteMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicant(@RequestParam int applicantId) {
		return applicantService.deleteApplicant(applicantId);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicate(@RequestParam int applicatId,
			@RequestBody Applicant applicant) {
		return applicantService.updateApplicat(applicatId, applicant);
	}
}
