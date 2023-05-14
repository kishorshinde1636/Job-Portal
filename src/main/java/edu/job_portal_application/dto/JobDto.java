package edu.job_portal_application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobDto {
	private long jobId;
	private String jobName;
	private String jobDiscription;
	private double jobSalary;
	private String company;

}
