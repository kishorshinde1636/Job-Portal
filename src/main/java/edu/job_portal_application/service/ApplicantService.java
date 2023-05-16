package edu.job_portal_application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.job_portal_application.dao.ApplicantDao;
import edu.job_portal_application.dto.ApplicantDto;
import edu.job_portal_application.entity.Applicant;
import edu.job_portal_application.util.ResponseStructure;

@Service
public class ApplicantService {

	@Autowired
	private ApplicantDao applicantDao;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<ApplicantDto>> addApplicant(Applicant applicant) {

		Applicant applicant2 = applicantDao.addApplicant(applicant);
		ApplicantDto applicantDto = this.modelMapper.map(applicant2, ApplicantDto.class);

		ResponseStructure<ApplicantDto> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage("Applicant added successfully.");
		responseStructure.setData(applicantDto);

		return new ResponseEntity<ResponseStructure<ApplicantDto>>(responseStructure, HttpStatus.CREATED);
	}

}
