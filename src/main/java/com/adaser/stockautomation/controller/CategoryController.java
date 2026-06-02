package com.adaser.stockautomation.controller;

import com.adaser.stockautomation.dto.CategoryDto;
import com.adaser.stockautomation.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("categoryDto", new CategoryDto());
        return "categories";
    }

    @PostMapping
    public String createCategory(@Valid @ModelAttribute CategoryDto categoryDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "categories";
        }

        categoryService.createCategory(categoryDto);
        return "redirect:/admin/categories";
    }
    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        model.addAttribute("categoryDto", categoryService.getCategoryDtoById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @Valid @ModelAttribute CategoryDto categoryDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "categories";
        }

        categoryService.updateCategory(id, categoryDto);
        return "redirect:/admin/categories";
    }
}