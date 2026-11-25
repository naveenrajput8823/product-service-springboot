package com.training.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name = "product_title")
    private String title;

    @Column(nullable = false, precision = 12, scale = 2) // decimal(12,2) -> 9999999999.99
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 100)
    private String category;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "product_images",
        joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "image_url", nullable = false, length = 2048)
    private List<String> images = new ArrayList<>();

    @Embedded
    private Rating rating;

    @Column(nullable = false)
    private Integer quantity;

    @CreationTimestamp
    private LocalDateTime mfg;

    @Column(name = "expiry")
    private LocalDate exp;

   
    public void setImages(List<String> images) {
        this.images = images != null
                ? new ArrayList<>(images)
                : new ArrayList<>();
    }
}


//https://www.guru99.com/database-normalization.html