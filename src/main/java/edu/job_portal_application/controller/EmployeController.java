package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.entity.Employer;
import edu.job_portal_application.service.EmployerServices;
import edu.job_portal_application.util.ResponseStructure;

@RestController
@RequestMapping("/employer")
public class EmployeController {

	@Autowired
	private EmployerServices employerServices;

	@PostMapping
	public ResponseEntity<ResponseStructure<Employer>> addEmployer(@RequestBody Employer employer) {
		return employerServices.addEmployer(employer);
	}
}
