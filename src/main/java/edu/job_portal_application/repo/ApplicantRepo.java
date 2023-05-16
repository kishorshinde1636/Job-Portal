package edu.job_portal_application.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.job_portal_application.entity.Applicant;

public interface ApplicantRepo extends JpaRepository<Applicant, Integer>{

}
