package edu.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.repo.ApplicantRepo;

@Repository
public class ApplicantDao {

	@Autowired
	private ApplicantRepo applicantRepo;

	public Applicant addApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);

	}

	public Applicant getApplicantById(int applicantId) {

		Optional<Applicant> applicant = applicantRepo.findById(applicantId);
		if (applicant.isPresent()) {
			return applicant.get();
		} else {
			return null;
		}
	}

	public void deleteApplicant(Applicant applicant) {
		applicantRepo.delete(applicant);

	}

}
