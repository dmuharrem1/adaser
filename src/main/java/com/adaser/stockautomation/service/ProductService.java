package com.adaser.stockautomation.service;

import com.adaser.stockautomation.dto.ProductDto;
import com.adaser.stockautomation.entity.Category;
import com.adaser.stockautomation.entity.Product;
import com.adaser.stockautomation.entity.Supplier;
import com.adaser.stockautomation.exception.ResourceNotFoundException;
import com.adaser.stockautomation.repository.CategoryRepository;
import com.adaser.stockautomation.repository.ProductRepository;
import com.adaser.stockautomation.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Supplier supplier = supplierRepository.findById(productDto.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        Product product = Product.builder()
                .productCode(productDto.getProductCode())
                .name(productDto.getName())
                .unit(productDto.getUnit())
                .quantity(productDto.getQuantity())
                .criticalStockLevel(productDto.getCriticalStockLevel())
                .purchasePrice(productDto.getPurchasePrice())
                .salePrice(productDto.getSalePrice())
                .category(category)
                .supplier(supplier)
                .build();

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    public List<Product> getCriticalStockProducts() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getQuantity() <= product.getCriticalStockLevel())
                .toList();
    }

    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}