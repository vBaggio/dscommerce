package com.vbaggio.dscommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {

	private Long id;
	
	@NotBlank(message = "Required")
	@Size(min = 3, max = 80, message = "Must be between 3 and 80 characters")
	private String name;
	
	@NotBlank(message = "Required")
	@Size(min = 10, message = "Must be at least 10 characters")
	private String description;
	
	@Positive(message = "Must be a positve value")
	private Double price;
	
	private String imgUrl;
	
	public ProductDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
