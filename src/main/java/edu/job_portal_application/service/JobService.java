package edu.job_portal_application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.EmployeDao;
import edu.job_portal_application.dao.JobDao;
import edu.job_portal_application.dao.SkillDao;
import edu.job_portal_application.dto.JobDto;
import edu.job_portal_application.entity.Employer;
import edu.job_portal_application.entity.Job;
import edu.job_portal_application.entity.Skill;
import edu.job_portal_application.exception.EmployerNotFoundByIdException;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class JobService {

	@Autowired
	private JobDao jobDao;

	@Autowired
	private EmployeDao employeDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SkillDao skillDao;

	public ResponseEntity<ResponseStructure<JobDto>> addJob(JobDto jobDto, long employerId, String[] skills) {

		Employer employer = employeDao.getEmployerById(employerId);
		if (employer != null) {

			Job job = this.modelMapper.map(jobDto, Job.class);
			job.setJobCreateDateTime(LocalDateTime.now());
			job.setEmployer(employer);

			job = jobDao.addJob(job);

//			jobDto.setJobId(job2.getJobId());

			/*
			 * checking if the skill is already present in the database, if present add same
			 * skill to the job, or else create the new skill
			 */

			List<Skill> jobSkills = new ArrayList<>();

			for (String skill : skills) {
				Skill exSkill = skillDao.getSkillByName(skill);

				if (exSkill != null) {
					if (!jobSkills.contains(exSkill)) {
						jobSkills.add(exSkill);
					}

				} else {
					Skill newSkill = new Skill();
					newSkill.setSkillName(skill);
					skillDao.addSkill(newSkill);
					jobSkills.add(newSkill);
				}
			}
			/*
			 * setting the exSkills list to the job
			 */
			job.setSkills(jobSkills);

			employer.getJobs().add(job);
			employeDao.addEmployer(employer);

			JobDto jobDto1 = this.modelMapper.map(job, JobDto.class);
			List<String> responseSkill = new ArrayList<>();
			for (Skill skill2 : job.getSkills()) {
				responseSkill.add(skill2.getSkillName());

			}

			jobDto1.setSkills(responseSkill);
			jobDto.setJobId(job.getJobId());

			ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Job added successfully!!");
			responseStructure.setData(jobDto);

			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.CREATED);

		} else {
			throw new EmployerNotFoundByIdException("Failed to Add Job!! ");
			// throw exception employerNotFoundById
		}

	}

	public ResponseEntity<ResponseStructure<JobDto>> getJobById(long jobId) {

		Job job = jobDao.getJobById(jobId);

		if (job != null) {
			JobDto response = this.modelMapper.map(job, JobDto.class);

			List<String> list = new ArrayList<>();

			for (Skill skill : job.getSkills()) {
				list.add(skill.getSkillName());
			}
			response.setSkills(list);

			ResponseStructure<JobDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Job Found.");
			responseStructure.setData(response);

			return new ResponseEntity<ResponseStructure<JobDto>>(responseStructure, HttpStatus.FOUND);

		}
		return null;
		// throw new JobNotFoundByIdException("Failed to find Job!!");
	}
}
