package com.training.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.training.dto.ProductRequestDTO;
import com.training.dto.ProductResponseDTO;
import com.training.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	// Entity -> Response DTO
	@Mapping(source = "rating.rate", target = "rate")
    @Mapping(source = "rating.count", target = "count")
	ProductResponseDTO toResponseDTO(Product product);

	// List<Entity> -> List<Response DTO>
	List<ProductResponseDTO> toResponseDTOList(List<Product> products);

	// Request DTO -> Entity
	@Mapping(target = "id", ignore = true)
	Product toEntity(ProductRequestDTO productRequest);
}