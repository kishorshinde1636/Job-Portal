package edu.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.job_portal_application.entity.Employer;
import edu.job_portal_application.repo.EmployerRepo;

@Repository
public class EmployeDao {

	@Autowired
	private EmployerRepo employerRepo;

	public Employer addEmployer(Employer employer) {
		return employerRepo.save(employer);
	}

	public Employer getEmployerById(long employerId) {

		Optional<Employer> employer = employerRepo.findById(employerId);

		if (employer.isEmpty()) {
			return null;
		} else {
			return employer.get();
		}

	}

}
