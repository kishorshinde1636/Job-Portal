package edu.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.EmployeDao;
import edu.job_portal_application.dao.JobDao;
import edu.job_portal_application.dto.EmployerDto;
import edu.job_portal_application.entity.Employer;
import edu.job_portal_application.entity.Job;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class EmployerServices {

	@Autowired
	private EmployeDao employeDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JobDao jobDao;

	public ResponseEntity<ResponseStructure<EmployerDto>> addEmployer(Employer employer) {
		Employer employer2 = employeDao.addEmployer(employer);

		EmployerDto employerDto = this.modelMapper.map(employer2, EmployerDto.class);
		ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Employer added successfully.");
		responseStructure.setData(employerDto);

		return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<EmployerDto>> getEmployerById(long employerId) {

		Employer employer = employeDao.getEmployerById(employerId);
		if (employer != null) {
			EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
			ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("Employer Found..");
			responseStructure.setData(employerDto);

			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.FOUND);
		} else {
			return null;
			// throw new EmployerNotFoundByIdException("Failed to find Employer!!");
		}
	}

	public ResponseEntity<ResponseStructure<EmployerDto>> deleteEmployerById(long employerId) {

		Employer employer = employeDao.getEmployerById(employerId);

		if (employer != null) {
			/*
			 * while deleting the employer, the jobs that he created are not deleted rather
			 * the employer has to null in job.
			 */
			for (Job job : employer.getJobs()) {
				job.setEmployer(null);
				jobDao.addJob(job);
			}

			employeDao.deleteEmployerById(employer);
			EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);

			ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Employer deleted successfully.");
			responseStructure.setData(employerDto);

			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);

		}

		return null;
		// throw new EmployerNotFoundByIdException("Failed to delete Employer!!");
	}

	public ResponseEntity<ResponseStructure<EmployerDto>> updateEmployer(Employer employer, long employerId) {

		Employer exEmployer = employeDao.getEmployerById(employerId);

		if (exEmployer != null) {

			employer.setEmployeId(exEmployer.getEmployeId());
			employer.setJobs(exEmployer.getJobs());

			employer = employeDao.addEmployer(employer);
			EmployerDto employerDto = this.modelMapper.map(employer, EmployerDto.class);
			ResponseStructure<EmployerDto> responseStructure = new ResponseStructure<>();
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Employer updated successfully.");
			responseStructure.setData(employerDto);

			return new ResponseEntity<ResponseStructure<EmployerDto>>(responseStructure, HttpStatus.OK);

		} else {
			return null;
			// throw new EmployerNotFoundByIdException("Failed to update Employer!!");
		}
	}

}
