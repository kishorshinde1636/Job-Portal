package edu.job_portal_application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployerNotFoundByIdException extends RuntimeException{
	
	private String messgae="Failed to Find Employer ";

}
