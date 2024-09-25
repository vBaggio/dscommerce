package com.vbaggio.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDTO extends CustomErrorDTO {
	
	private List<FieldMessageDTO> errors = new ArrayList<>();
	
	public ValidationErrorDTO(Instant moment, String message, String path) {
		super(moment, message, path);
	}

	public List<FieldMessageDTO> getErrors() {
		return errors;
	}
	
	public void addError(FieldMessageDTO error) {
		errors.add(error);
	}

}
