package com.adaser.stockautomation.service;

import com.adaser.stockautomation.dto.CategoryDto;
import com.adaser.stockautomation.entity.Category;
import com.adaser.stockautomation.exception.ResourceNotFoundException;
import com.adaser.stockautomation.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();

        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        categoryRepository.delete(category);
    }
    public CategoryDto getCategoryDtoById(Long id) {
        Category category = getCategoryById(id);

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category updateCategory(Long id, CategoryDto categoryDto) {
        Category category = getCategoryById(id);
        category.setName(categoryDto.getName());
        return categoryRepository.save(category);
    }
}
