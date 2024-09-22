package com.vbaggio.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vbaggio.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
