package edu.job_portal_application.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class jobNotFoundByIdException extends RuntimeException {

	String message;
}
