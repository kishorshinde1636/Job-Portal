package edu.job_portal_application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.job_portal_application.entity.JobApplication;
import edu.job_portal_application.repo.JobApplicationRepo;

@Repository
public class JobApplicationDao {

	@Autowired
	private JobApplicationRepo applicationRepo;

	public JobApplication saveJobApplication(JobApplication application) {
		return applicationRepo.save(application);
	}

}
