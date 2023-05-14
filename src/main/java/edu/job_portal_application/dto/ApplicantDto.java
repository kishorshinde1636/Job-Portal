package edu.job_portal_application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApplicantDto {
	
	private int applicantId;
	private String applicantName;
	private String applicantEmail;
	private long applicantPhNo;

}
