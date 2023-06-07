package edu.job_portal_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.job_portal_application.dto.EmployerDto;
import edu.job_portal_application.entity.Employer;
import edu.job_portal_application.service.EmployerServices;
import edu.job_portal_application.util.ResponseStructure;

@RestController
@RequestMapping("/employer")
public class EmployeController {

	@Autowired
	private EmployerServices employerServices;

	@PostMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> addEmployer(@RequestBody Employer employer) {
		return employerServices.addEmployer(employer);
	}
	
	@GetMapping()
	public ResponseEntity<ResponseStructure<EmployerDto>> getEmployerById(@RequestParam long employerId)
	{
		return employerServices.getEmployerById(employerId);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployerById(@RequestParam long employerId)
	{
		return employerServices.deleteEmployerById(employerId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<EmployerDto>> updateEmployer(@RequestBody Employer employer,@RequestParam long employerId)
	{
		return employerServices.updateEmployer(employer,employerId);
	}
	
}
