package com.vbaggio.dscommerce.dto;

import java.time.Instant;

public class CustomErrorDTO {
	private Instant moment;
	private String error;
	private String path;

	public CustomErrorDTO(Instant moment, String error, String path) {
		super();
		this.moment = moment;
		this.error = error;
		this.path = path;
	}

	public Instant getMoment() {
		return moment;
	}

	public String getError() {
		return error;
	}

	public String getPath() {
		return path;
	}

}
