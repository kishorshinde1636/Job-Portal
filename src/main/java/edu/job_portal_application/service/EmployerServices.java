package edu.job_portal_application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.EmployeDao;
import edu.job_portal_application.entity.Employer;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class EmployerServices {

	@Autowired
	private EmployeDao employeDao;

	public ResponseEntity<ResponseStructure<Employer>> addEmployer(Employer employer) {
		Employer employer2 = employeDao.addEmployer(employer);

		ResponseStructure<Employer> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer added successfully.");
		responseStructure.setData(employer2);

		return new ResponseEntity<ResponseStructure<Employer>>(responseStructure, HttpStatus.CREATED);
	}

}
