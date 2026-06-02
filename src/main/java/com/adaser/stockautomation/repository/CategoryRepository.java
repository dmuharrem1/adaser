package com.adaser.stockautomation.repository;

import com.adaser.stockautomation.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}