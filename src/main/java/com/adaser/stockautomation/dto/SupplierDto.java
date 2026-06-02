package com.adaser.stockautomation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierDto {

    @NotBlank(message = "Company name cannot be empty")
    private String companyName;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @NotBlank(message = "Address cannot be empty")
    private String address;
}