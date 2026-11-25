package com.training.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

    private Long id;

    private String title;

    private BigDecimal price;

    private String description;

    private String category;

    private List<String> images = new ArrayList<>();

    private BigDecimal rate;
    private long count;
    
    private Integer quantity;

    private LocalDateTime mfg;

    private LocalDate exp;
}