package com.vbaggio.dscommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vbaggio.dscommerce.dto.CustomErrorDTO;
import com.vbaggio.dscommerce.dto.FieldMessageDTO;
import com.vbaggio.dscommerce.dto.ValidationErrorDTO;
import com.vbaggio.dscommerce.services.exceptions.DatabaseException;
import com.vbaggio.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	private CustomErrorDTO customError(Exception e, HttpServletRequest request) {
		return new CustomErrorDTO(Instant.now(), e.getMessage(), request.getRequestURI());
	}
	
	private ValidationErrorDTO validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationErrorDTO result = new ValidationErrorDTO(Instant.now(), "Validation error", request.getRequestURI());
		
		e.getBindingResult().getFieldErrors()
			.forEach(fe -> result.addError(new FieldMessageDTO(fe.getField(), fe.getDefaultMessage())));
		
		return result;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomErrorDTO> ResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError(e, request));
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomErrorDTO> Database(DatabaseException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customError(e, request));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomErrorDTO> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError(e, request));
	}
}
