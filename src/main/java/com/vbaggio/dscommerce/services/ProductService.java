package com.vbaggio.dscommerce.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vbaggio.dscommerce.dto.ProductDTO;
import com.vbaggio.dscommerce.entities.Product;
import com.vbaggio.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = repository.findById(id).get();
		return modelMapper.map(product, ProductDTO.class);
	}

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> products = repository.findAll(pageable);
		return products.map(x -> modelMapper.map(x, ProductDTO.class));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product product = modelMapper.map(dto, Product.class);
		product = repository.save(product);
		return modelMapper.map(product, ProductDTO.class);
	}
}
