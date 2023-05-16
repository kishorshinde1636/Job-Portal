package edu.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.ApplicantDao;
import edu.job_portal_application.dao.ResumeDao;
import edu.job_portal_application.dto.ResumeDto;
import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.entity.Resume;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class ResumeService {

	@Autowired
	private ResumeDao resumeDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApplicantDao applicantDao;

	public ResponseEntity<ResponseStructure<Resume>> saveResume(int applicantId, ResumeDto resumeDto) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {
			Resume existingResume = applicant.getResume();
			Resume newResume = this.modelMapper.map(resumeDto, Resume.class);
			if (existingResume != null) {
				newResume.setResumeId(existingResume.getResumeId());

			}
			newResume.setApplicant(applicant);
			newResume = resumeDao.saveResume(newResume);
			applicant.setResume(newResume);
			applicantDao.addApplicant(applicant);

			ResponseStructure<Resume> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Resume added successfully!!");
			responseStructure.setData(newResume);
			return new ResponseEntity<ResponseStructure<Resume>>(responseStructure, HttpStatus.CREATED);
		} else {
			return null;
			// throw new ApplicantNotFoundByIdException("Failed to add resume!!");
		}

	}

}
