package edu.job_portal_application.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.ApplicantDao;
import edu.job_portal_application.dao.JobApplicationDao;
import edu.job_portal_application.dao.JobDao;
import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.entity.Job;
import edu.job_portal_application.entity.JobApplication;
import edu.job_portal_application.exception.ApplicantNotfoundByIdException;
import edu.job_portal_application.exception.jobNotFoundByIdException;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class JobApplicationService {

	@Autowired
	private JobApplicationDao applicationDao;

	@Autowired
	private JobDao jobDao;

	@Autowired
	private ApplicantDao applicantDao;

	public ResponseEntity<ResponseStructure<JobApplication>> saveJobApplication(int applicantId, long jobId) {
		Applicant applicant = applicantDao.getApplicantById(applicantId);

		if (applicant != null) {
			Job job = jobDao.getJobById(jobId);

			if (job != null) {
				JobApplication application = new JobApplication();
				application.setJobApplicationDateTime(LocalDateTime.now());
				application.setJob(job);
				application.setApplicant(applicant);

				// saving the jobApplication object
				application = applicationDao.saveJobApplication(application);

				// setting and updating jobApplication for the job
				job.getJobApplication().add(application);
				jobDao.addJob(job);

				// setting and updating jobApplication for the Applicant
				job.getJobApplication().add(application);
				applicantDao.addApplicant(applicant);

				ResponseStructure<JobApplication> responseStructure = new ResponseStructure<>();
				responseStructure.setStatusCode(HttpStatus.CREATED.value());
				responseStructure.setMessage("Job Application added Successfully!!");
				responseStructure.setData(application);

				return new ResponseEntity<ResponseStructure<JobApplication>>(responseStructure, HttpStatus.CREATED);

			} else {
				//return null;
				 throw new jobNotFoundByIdException("Failed to create job Application!!");
			}

		} else {
			 throw new ApplicantNotfoundByIdException("Failed to create Job Application!!");
			//return null;
		}
	}

}
