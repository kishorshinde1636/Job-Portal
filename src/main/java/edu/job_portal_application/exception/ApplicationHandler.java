package edu.job_portal_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.job_portal_application.util.ResponseStructure;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EmployerNotFoundByIdException.class)
	public ResponseEntity<ResponseStructure<String>> userIdNotFound(EmployerNotFoundByIdException ex) {
		ResponseStructure<String> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(ex.getMessgae());
		responseStructure.setData("Employer not found with the requested Id!!");

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

}
