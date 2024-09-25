package com.vbaggio.dscommerce.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vbaggio.dscommerce.dto.ProductDTO;
import com.vbaggio.dscommerce.entities.Product;
import com.vbaggio.dscommerce.repositories.ProductRepository;
import com.vbaggio.dscommerce.services.exceptions.DatabaseException;
import com.vbaggio.dscommerce.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
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

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {

		try {
			Product product = repository.getReferenceById(id);

			modelMapper.getConfiguration().setSkipNullEnabled(true);
			modelMapper.typeMap(ProductDTO.class, Product.class).addMappings(mapper -> mapper.skip(Product::setId));

			modelMapper.map(dto, product);

			modelMapper.getConfiguration().setSkipNullEnabled(false);

			product = repository.save(product);

			return modelMapper.map(product, ProductDTO.class);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException();
		}

	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!repository.existsById(id))
			throw new ResourceNotFoundException();
		
		try {
			repository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Referential integrity violation.");
		}
	}
}
