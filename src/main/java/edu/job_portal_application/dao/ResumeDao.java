package edu.job_portal_application.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.job_portal_application.entity.Resume;
import edu.job_portal_application.repo.ResumeRepo;

@Repository
public class ResumeDao {

	@Autowired
	private ResumeRepo resumeRepo;

	public Resume saveResume(Resume resume) {
		return resumeRepo.save(resume);
	}

	public Optional<Resume> getResumeById(int resumeId) {

		return resumeRepo.findById(resumeId);
	}

	public void deleteResume(Resume resume) {
		
		resumeRepo.delete(resume);
		
	}

}
