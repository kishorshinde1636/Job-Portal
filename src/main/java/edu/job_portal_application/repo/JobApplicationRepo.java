package edu.job_portal_application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.job_portal_application.entity.JobApplication;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Integer> {

}
