package edu.job_portal_application.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;




@Setter
@Getter
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
	
	@ManyToMany
	@JoinTable
	private List<Skill> skills;
	
	@OneToMany(mappedBy = "job")
	@JsonIgnore
	private List<JobApplication> jobApplication;

	@ManyToOne
	@JoinColumn
	private Employer employer;
	
}
