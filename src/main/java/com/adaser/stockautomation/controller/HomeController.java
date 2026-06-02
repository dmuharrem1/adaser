package com.adaser.stockautomation.controller;

import com.adaser.stockautomation.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalProductCount", productService.getTotalProductCount());
        model.addAttribute("totalStockQuantity", productService.getTotalStockQuantity());
        model.addAttribute("totalStockValue", productService.getTotalStockValue());
        model.addAttribute("criticalProductCount", productService.getCriticalStockProducts().size());
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}