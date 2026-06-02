package com.adaser.stockautomation.config;

import com.adaser.stockautomation.entity.Category;
import com.adaser.stockautomation.entity.Product;
import com.adaser.stockautomation.entity.Supplier;
import com.adaser.stockautomation.repository.CategoryRepository;
import com.adaser.stockautomation.repository.ProductRepository;
import com.adaser.stockautomation.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            Category adhesive = categoryRepository.save(Category.builder().name("Yapıştırıcılar").build());
            Category mortar = categoryRepository.save(Category.builder().name("Tamir Harçları").build());
            Category waterproofing = categoryRepository.save(Category.builder().name("Su Yalıtım Ürünleri").build());
            Category primer = categoryRepository.save(Category.builder().name("Astarlar").build());

            Supplier supplierOne = supplierRepository.save(Supplier.builder()
                    .companyName("Adaser Ana Depo")
                    .phone("0248 000 00 00")
                    .address("Burdur Organize Sanayi Bölgesi")
                    .build());

            Supplier supplierTwo = supplierRepository.save(Supplier.builder()
                    .companyName("Kimya Tedarik Ltd.")
                    .phone("0242 111 22 33")
                    .address("Antalya")
                    .build());

            productRepository.save(Product.builder()
                    .productCode("ADS-001")
                    .name("Seramik Yapıştırıcı")
                    .unit("kg")
                    .quantity(250)
                    .criticalStockLevel(50)
                    .purchasePrice(120.0)
                    .salePrice(180.0)
                    .category(adhesive)
                    .supplier(supplierOne)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("ADS-002")
                    .name("Derz Dolgu")
                    .unit("kg")
                    .quantity(180)
                    .criticalStockLevel(40)
                    .purchasePrice(90.0)
                    .salePrice(140.0)
                    .category(mortar)
                    .supplier(supplierOne)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("ADS-003")
                    .name("Kristalize Su Yalıtımı")
                    .unit("kg")
                    .quantity(35)
                    .criticalStockLevel(50)
                    .purchasePrice(200.0)
                    .salePrice(320.0)
                    .category(waterproofing)
                    .supplier(supplierTwo)
                    .build());

            productRepository.save(Product.builder()
                    .productCode("ADS-004")
                    .name("Brüt Beton Astarı")
                    .unit("lt")
                    .quantity(90)
                    .criticalStockLevel(25)
                    .purchasePrice(150.0)
                    .salePrice(230.0)
                    .category(primer)
                    .supplier(supplierTwo)
                    .build());
        }
    }
}