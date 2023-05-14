package edu.job_portal_application.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int applicantId;
	private String applicantName;
	private String applicantEmail;
	private int applicatPassword;
	private long applicantPhNo;
	
	@OneToMany
	private List<JobApplication> jobApplications;

	@OneToOne
	private Resume resume;

	
}
