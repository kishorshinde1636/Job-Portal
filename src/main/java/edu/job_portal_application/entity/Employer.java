
package edu.job_portal_application.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class Employer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeId;
	private String employeName;
	private String employeEmail;
	private long employePasssword;
	
	
	@OneToMany(mappedBy = "employer")
	private List<Job> jobs;
	
	

}
