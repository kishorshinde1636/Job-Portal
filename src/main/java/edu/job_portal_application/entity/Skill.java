package edu.job_portal_application.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Skill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int skillId;
	private String skillName;
	
	@ManyToMany(mappedBy = "skills")
	@JsonIgnore
	private List<Job> jobs;
	
	@ManyToMany(mappedBy = "skills")
	@JsonIgnore
	private List<Resume> resumes;

}
