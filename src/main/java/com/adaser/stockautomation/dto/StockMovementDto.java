package com.adaser.stockautomation.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockMovementDto {

    @NotNull(message = "Product must be selected")
    private Long productId;

    @NotBlank(message = "Movement type cannot be empty")
    private String movementType;

    @NotNull(message = "Quantity cannot be empty")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    private String description;
}