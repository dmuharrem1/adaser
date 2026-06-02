package com.adaser.stockautomation.controller;

import com.adaser.stockautomation.dto.StockMovementDto;
import com.adaser.stockautomation.service.ProductService;
import com.adaser.stockautomation.service.StockMovementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.adaser.stockautomation.exception.InsufficientStockException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/staff/movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;
    private final ProductService productService;

    @GetMapping
    public String listMovements(Model model) {
        model.addAttribute("movements", stockMovementService.getAllMovements());
        model.addAttribute("movementDto", new StockMovementDto());
        model.addAttribute("products", productService.getAllProducts());
        return "movements";
    }

    @PostMapping
    public String createMovement(@Valid @ModelAttribute("movementDto") StockMovementDto movementDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("movements", stockMovementService.getAllMovements());
            model.addAttribute("products", productService.getAllProducts());
            return "movements";
        }

        try {
            stockMovementService.createMovement(movementDto);
        } catch (InsufficientStockException exception) {
            model.addAttribute("stockError", exception.getMessage());
            model.addAttribute("movements", stockMovementService.getAllMovements());
            model.addAttribute("products", productService.getAllProducts());
            return "movements";
        }

        return "redirect:/staff/movements";
    }
}