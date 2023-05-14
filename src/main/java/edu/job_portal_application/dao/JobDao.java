package edu.job_portal_application.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.job_portal_application.entity.Job;
import edu.job_portal_application.repo.JobRepo;

@Repository
public class JobDao {

	@Autowired
	private JobRepo jobRepo;

	public Job addJob(Job job) {
		return jobRepo.save(job);
	}

}
