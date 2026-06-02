package com.adaser.stockautomation.service;

import com.adaser.stockautomation.dto.StockMovementDto;
import com.adaser.stockautomation.entity.Product;
import com.adaser.stockautomation.entity.StockMovement;
import com.adaser.stockautomation.exception.ResourceNotFoundException;
import com.adaser.stockautomation.repository.ProductRepository;
import com.adaser.stockautomation.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public List<StockMovement> getAllMovements() {
        return stockMovementRepository.findAll();
    }

    public StockMovement createMovement(StockMovementDto stockMovementDto) {
        Product product = productRepository.findById(stockMovementDto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if (stockMovementDto.getMovementType().equalsIgnoreCase("OUT")) {
            if (product.getQuantity() < stockMovementDto.getQuantity()) {
                throw new RuntimeException("Not enough stock");
            }

            product.setQuantity(product.getQuantity() - stockMovementDto.getQuantity());
        } else if (stockMovementDto.getMovementType().equalsIgnoreCase("IN")) {
            product.setQuantity(product.getQuantity() + stockMovementDto.getQuantity());
        }

        productRepository.save(product);

        StockMovement stockMovement = StockMovement.builder()
                .product(product)
                .movementType(stockMovementDto.getMovementType())
                .quantity(stockMovementDto.getQuantity())
                .movementDate(LocalDateTime.now())
                .description(stockMovementDto.getDescription())
                .build();

        return stockMovementRepository.save(stockMovement);
    }

    public List<StockMovement> getMovementsByProduct(Long productId) {
        return stockMovementRepository.findByProductId(productId);
    }
}