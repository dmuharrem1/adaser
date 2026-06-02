package com.adaser.stockautomation.service;

import com.adaser.stockautomation.dto.SupplierDto;
import com.adaser.stockautomation.entity.Supplier;
import com.adaser.stockautomation.exception.ResourceNotFoundException;
import com.adaser.stockautomation.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier createSupplier(SupplierDto supplierDto) {
        Supplier supplier = Supplier.builder()
                .companyName(supplierDto.getCompanyName())
                .phone(supplierDto.getPhone())
                .address(supplierDto.getAddress())
                .build();

        return supplierRepository.save(supplier);
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }
}