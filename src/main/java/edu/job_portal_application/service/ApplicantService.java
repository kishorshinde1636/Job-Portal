package edu.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.ApplicantDao;
import edu.job_portal_application.dao.JobApplicationDao;
import edu.job_portal_application.dao.ProjectDao;
import edu.job_portal_application.dao.ResumeDao;
import edu.job_portal_application.dto.ApplicantDto;
import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.entity.JobApplication;
import edu.job_portal_application.entity.Project;
import edu.job_portal_application.entity.Resume;
import edu.job_portal_application.exception.ApplicantNotfoundByIdException;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ResumeDao resumeDao;

	@Autowired
	private JobApplicationDao jobApplicationDao;

	@Autowired
	private ProjectDao projectDao;

	public ResponseEntity<ResponseStructure<ApplicantDto>> addApplicant(Applicant applicant) {

		Applicant applicant2 = applicantDao.addApplicant(applicant);
		ApplicantDto applicantDto = this.modelMapper.map(applicant2, ApplicantDto.class);

		ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Applicant added successfully.");
		responseStructure.setData(applicantDto);

		return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> getApplicantById(int applicantId) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {

			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);

			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Applicant Found.");
			responseStructure.setData(applicantDto);

			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			// return null;
			throw new ApplicantNotfoundByIdException("Failed to find Applicant!!");
		}
	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> deleteApplicant(int applicantId) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {

			for (JobApplication jobApplication : applicant.getJobApplications()) {
				jobApplication.setApplicant(null);

				jobApplicationDao.saveJobApplication(jobApplication);

			}

			applicantDao.deleteApplicant(applicant);

			Resume resume = applicant.getResume();

			if (resume != null) {

				resume.setSkills(null);

				resumeDao.saveResume(resume);

				for (Project project : applicant.getResume().getProjects()) {
					projectDao.deleteProject(project);

				}
				resumeDao.deleteResume(resume);

			}

			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);
			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant deleted successfully.");
			responseStructure.setData(applicantDto);

			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
		}

		else {
			throw new ApplicantNotfoundByIdException("Failed to delete Applicant!!");

			// return null;
		}

	}

	public ResponseEntity<ResponseStructure<ApplicantDto>> updateApplicat(int applicantId, Applicant applicant) {
		Applicant exApplicant = applicantDao.getApplicantById(applicantId);

		if (exApplicant != null) {
			applicant.setApplicantId(exApplicant.getApplicantId());
			applicant.setJobApplications(exApplicant.getJobApplications());
			applicant.setResume(exApplicant.getResume());
			applicantDao.addApplicant(applicant);

			ApplicantDto applicantDto = this.modelMapper.map(applicant, ApplicantDto.class);

			ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Applicant updated successfully.");
			responseStructure.setData(applicantDto);

			return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.OK);
		} else {
			// return null;
			throw new ApplicantNotfoundByIdException("Failed to update Applicant!!");
		}
	}

}
