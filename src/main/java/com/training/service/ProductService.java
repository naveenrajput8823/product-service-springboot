package com.training.service;

import java.util.List;

import com.training.dto.ProductRequestDTO;
import com.training.dto.ProductResponseDTO;

public interface ProductService {
	ProductResponseDTO addProduct(ProductRequestDTO product);
	List<ProductResponseDTO> getAllProducts();
	ProductResponseDTO updateProduct(Long id, ProductRequestDTO product);
	boolean deleteProduct(Long id);
}
