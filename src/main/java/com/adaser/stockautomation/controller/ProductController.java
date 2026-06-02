package com.adaser.stockautomation.controller;

import com.adaser.stockautomation.dto.ProductDto;
import com.adaser.stockautomation.service.CategoryService;
import com.adaser.stockautomation.service.ProductService;
import com.adaser.stockautomation.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/staff/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "product-form";
    }

    @PostMapping
    public String createProduct(@Valid @ModelAttribute ProductDto productDto,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("suppliers", supplierService.getAllSuppliers());
            return "product-form";
        }

        productService.createProduct(productDto);
        return "redirect:/staff/products";
    }

    @GetMapping("/critical")
    public String criticalProducts(Model model) {
        model.addAttribute("products", productService.getCriticalStockProducts());
        return "critical-products";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String keyword, Model model) {
        model.addAttribute("products", productService.searchProducts(keyword));
        return "products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/staff/products";
    }
    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        model.addAttribute("productDto", productService.getProductDtoById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        return "product-form";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute ProductDto productDto,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("suppliers", supplierService.getAllSuppliers());
            return "product-form";
        }

        productService.updateProduct(id, productDto);
        return "redirect:/staff/products";
    }
    @GetMapping("/filter")
    public String filterProducts(@RequestParam String category,
                                 Model model) {

        model.addAttribute(
                "products",
                productService.getProductsByCategory(category)
        );


        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        return "products";
    }
}