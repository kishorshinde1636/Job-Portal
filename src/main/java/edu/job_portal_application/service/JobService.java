package edu.job_portal_application.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.EmployeDao;
import edu.job_portal_application.dao.JobDao;
import edu.job_portal_application.dto.JobDto;
import edu.job_portal_application.entity.Employer;
import edu.job_portal_application.entity.Job;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class JobService {

	@Autowired
	private JobDao jobDao;

	@Autowired
	private EmployeDao employeDao;

	public ResponseEntity<ResponseStructure<Job>> addJob(JobDto jobDto, long employerId) {

		Employer employer = employeDao.getEmployerById(employerId);
		if (employer != null) {
			Job job = new Job();
			job.setJobName(jobDto.getJobName());
			job.setJobDiscription(jobDto.getJobDiscription());
			job.setCompany(jobDto.getCompany());
			job.setJobSalary(jobDto.getJobSalary());
			job.setJobCreateDateTime(LocalDateTime.now());
			job.setEmployer(employer);

			Job job2=jobDao.addJob(job);

			employer.getJobs().add(job);
			employeDao.addEmployer(employer);

			 jobDto.setJobId(job2.getJobId());

			ResponseStructure<Job> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job added successfully!!");
			responseStructure.setData(jobDto);

			return new ResponseEntity<ResponseStructure<Job>>(responseStructure, HttpStatus.CREATED);

		} else {
			return null;
			// throw exception employerNotFoundById
		}

	}

}
