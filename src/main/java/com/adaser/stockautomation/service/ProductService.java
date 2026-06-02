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
    public long getTotalProductCount() {
        return productRepository.count();
    }

    public int getTotalStockQuantity() {
        return productRepository.findAll()
                .stream()
                .mapToInt(Product::getQuantity)
                .sum();
    }

    public double getTotalStockValue() {
        return productRepository.findAll()
                .stream()
                .mapToDouble(product -> product.getQuantity() * product.getSalePrice())
                .sum();
    }
    public ProductDto getProductDtoById(Long id) {
        Product product = getProductById(id);

        return ProductDto.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .name(product.getName())
                .unit(product.getUnit())
                .quantity(product.getQuantity())
                .criticalStockLevel(product.getCriticalStockLevel())
                .purchasePrice(product.getPurchasePrice())
                .salePrice(product.getSalePrice())
                .categoryId(product.getCategory().getId())
                .supplierId(product.getSupplier().getId())
                .build();
    }

    public Product updateProduct(Long id, ProductDto productDto) {
        Product product = getProductById(id);

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Supplier supplier = supplierRepository.findById(productDto.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        product.setProductCode(productDto.getProductCode());
        product.setName(productDto.getName());
        product.setUnit(productDto.getUnit());
        product.setQuantity(productDto.getQuantity());
        product.setCriticalStockLevel(productDto.getCriticalStockLevel());
        product.setPurchasePrice(productDto.getPurchasePrice());
        product.setSalePrice(productDto.getSalePrice());
        product.setCategory(category);
        product.setSupplier(supplier);

        return productRepository.save(product);
    }
    public List<Product> getProductsByCategory(String categoryName) {
        return productRepository.findByCategory_Name(categoryName);
    }
    public List<String> getCategoryChartLabels() {
        return productRepository.findAll()
                .stream()
                .map(product -> product.getCategory().getName())
                .distinct()
                .toList();
    }

    public List<Integer> getCategoryChartValues() {
        return getCategoryChartLabels()
                .stream()
                .map(categoryName -> productRepository.findAll()
                        .stream()
                        .filter(product -> product.getCategory().getName().equals(categoryName))
                        .mapToInt(Product::getQuantity)
                        .sum())
                .toList();
    }

    public List<String> getTopProductLabels() {
        return productRepository.findAll()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getQuantity(), a.getQuantity()))
                .limit(5)
                .map(Product::getName)
                .toList();
    }

    public List<Integer> getTopProductValues() {
        return productRepository.findAll()
                .stream()
                .sorted((a, b) -> Integer.compare(b.getQuantity(), a.getQuantity()))
                .limit(5)
                .map(Product::getQuantity)
                .toList();
    }
}