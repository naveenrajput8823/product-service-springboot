package com.training.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Rating {

    @Column(name = "rating_rate", precision = 2, scale = 1)
    private BigDecimal rate;

    @Column(name = "rating_count")
    private Integer count;
}