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
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resumeId;
	private String summary;
	private String qulalification;
	private String socialProfile1;
	private String socialProfile2;
	private String socialProfile3;
	private String certification;
	
	@OneToOne
	private Applicant applicant;

	@OneToMany
	private List<Skill> skills;

	@OneToMany
	private List<Project> projects;
}
