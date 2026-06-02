package com.adaser.stockautomation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String movementType;

    private Integer quantity;

    private LocalDateTime movementDate;

    private String description;

    @ManyToOne
    private Product product;
}