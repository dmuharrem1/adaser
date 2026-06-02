package com.adaser.stockautomation.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private Long id;

    @NotBlank(message = "Product code cannot be empty")
    private String productCode;

    @NotBlank(message = "Product name cannot be empty")
    private String name;

    @NotBlank(message = "Unit cannot be empty")
    private String unit;

    @NotNull(message = "Quantity cannot be empty")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "Critical stock level cannot be empty")
    @Min(value = 0, message = "Critical stock level cannot be negative")
    private Integer criticalStockLevel;

    @NotNull(message = "Purchase price cannot be empty")
    @DecimalMin(value = "0.0", message = "Purchase price cannot be negative")
    private Double purchasePrice;

    @NotNull(message = "Sale price cannot be empty")
    @DecimalMin(value = "0.0", message = "Sale price cannot be negative")
    private Double salePrice;

    private String imageName;

    @NotNull(message = "Category must be selected")
    private Long categoryId;

    @NotNull(message = "Supplier must be selected")
    private Long supplierId;

}