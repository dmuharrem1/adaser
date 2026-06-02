package com.adaser.stockautomation.controller;

import com.adaser.stockautomation.service.ProductService;
import com.adaser.stockautomation.service.StockMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;
    private final StockMovementService stockMovementService;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categoryChartLabels", productService.getCategoryChartLabels());
        model.addAttribute("categoryChartValues", productService.getCategoryChartValues());
        model.addAttribute("topProductLabels", productService.getTopProductLabels());
        model.addAttribute("topProductValues", productService.getTopProductValues());
        model.addAttribute("totalProductCount", productService.getTotalProductCount());
        model.addAttribute("totalStockQuantity", productService.getTotalStockQuantity());
        model.addAttribute("totalStockValue", productService.getTotalStockValue());
        model.addAttribute("criticalProductCount", productService.getCriticalStockProducts().size());
        model.addAttribute(
                "recentMovements",
                stockMovementService.getAllMovements()
                        .stream()
                        .sorted((a,b)->b.getMovementDate().compareTo(a.getMovementDate()))
                        .limit(5)
                        .toList()
        );

        model.addAttribute(
                "criticalProducts",
                productService.getCriticalStockProducts()
        );
        model.addAttribute(
                "topProducts",
                productService.getAllProducts()
                        .stream()
                        .sorted((a,b)->Integer.compare(
                                b.getQuantity(),
                                a.getQuantity()
                        ))
                        .limit(5)
                        .toList()
        );
        return "index";

    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

}