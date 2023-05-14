package edu.job_portal_application.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobId;
	private String jobName;
	private String jobDiscription;
	private double jobSalary;
	private String company;
	private LocalDateTime jobCreateDateTime;
	
	@OneToMany
	private List<JobApplication> jobApplication;

	@ManyToOne
	private Employer employer;
	
}
