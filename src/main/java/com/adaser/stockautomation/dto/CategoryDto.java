package com.adaser.stockautomation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    @NotBlank(message = "Category name cannot be empty")
    private String name;
}