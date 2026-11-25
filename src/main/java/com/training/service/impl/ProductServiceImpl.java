package com.training.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.training.dto.ProductRequestDTO;
import com.training.dto.ProductResponseDTO;
import com.training.entity.Product;
import com.training.entity.Rating;
import com.training.mapper.ProductMapper;
import com.training.repository.ProductRepository;
import com.training.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private final ProductRepository prodRepository;
	private final ProductMapper productMapper;
	public ProductServiceImpl(ProductRepository prodRepository,ProductMapper productMapper) {
		this.prodRepository = prodRepository;
		this.productMapper=productMapper;
	}


	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO product) {

	    // 1. Request DTO -> Entity
	    Product p = new Product();

	    p.setTitle(product.getTitle());
	    p.setPrice(product.getPrice());
	    p.setDescription(product.getDescription());
	    p.setCategory(product.getCategory());
	    p.setImages(product.getImages());
	    p.setQuantity(product.getQuantity());
	    p.setMfg(product.getMfg());
	    p.setExp(product.getExp());

	    // Map embedded Rating DTO -> Rating entity
	    if (product.getRating() != null) {
	        Rating rating = new Rating();

	        rating.setRate(product.getRating().getRate());
	        rating.setCount(product.getRating().getCount());

	        p.setRating(rating);
	    }

	    // 2. Save entity in database
	    Product savedProd = prodRepository.save(p);

	    // 3. Saved Entity -> Response DTO
	    ProductResponseDTO response = new ProductResponseDTO();

	    response.setId(savedProd.getId());
	    response.setTitle(savedProd.getTitle());
	    response.setPrice(savedProd.getPrice());
	    response.setDescription(savedProd.getDescription());
	    response.setCategory(savedProd.getCategory());
	    response.setImages(savedProd.getImages());
	    response.setQuantity(savedProd.getQuantity());
	    response.setMfg(savedProd.getMfg());
	    response.setExp(savedProd.getExp());

	    // Map embedded Rating entity -> Rating response DTO
	    if (savedProd.getRating() != null) {
	        
	    	response.setRate(
	                savedProd.getRating().getRate());

	    	response.setCount(
	                savedProd.getRating().getCount());
	    }

	    return response;
	}


	  @Override
	    public List<ProductResponseDTO> getAllProducts() {

	        List<Product> allProducts =
	                prodRepository.findAll();

	        return productMapper.toResponseDTOList(allProducts);
	    }


	  @Override
	  public ProductResponseDTO updateProduct(Long id, ProductRequestDTO product) {

		  Optional<Product> byId = prodRepository.findById(id);
		  if(!byId.isPresent())
		  {
			  throw new RuntimeException("product not found!");
		  }
		  
		  return productMapper.toResponseDTO(byId.get());
	  }


	  @Override
	  public boolean deleteProduct(Long id) {
		  Optional<Product> byId = prodRepository.findById(id);
		  if(!byId.isPresent())
		  {
			  throw new RuntimeException("product not found!");
		  }
		  prodRepository.deleteById(id);
		  return true;
	  }
	
}
