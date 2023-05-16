package edu.job_portal_application.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int jobApplicationId;
	private LocalDateTime jobApplicationDateTime;
	
	@ManyToOne
	@JoinColumn
	private Job job;

	@ManyToOne
	@JoinColumn
	private Applicant applicant;

	
}
