package com.adaser.stockautomation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productCode;

    private String name;

    private String unit;

    private Integer quantity;

    private Integer criticalStockLevel;

    private Double purchasePrice;

    private Double salePrice;

    private String imageName;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Supplier supplier;

}