package com.adaser.stockautomation.repository;

import com.adaser.stockautomation.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    List<Supplier> findByCompanyNameContainingIgnoreCase(String companyName);
}