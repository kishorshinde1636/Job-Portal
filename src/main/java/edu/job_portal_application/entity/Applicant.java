package edu.job_portal_application.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@OneToMany(mappedBy = "applicant")
	@JsonIgnore
	private List<JobApplication> jobApplications;

	@OneToOne
	@JoinColumn
	private Resume resume;

	
}
