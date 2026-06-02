package com.adaser.stockautomation.controller;

import com.adaser.stockautomation.dto.SupplierDto;
import com.adaser.stockautomation.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    @GetMapping
    public String listSuppliers(Model model) {
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        model.addAttribute("supplierDto", new SupplierDto());
        return "suppliers";
    }

    @PostMapping
    public String createSupplier(@Valid @ModelAttribute SupplierDto supplierDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("suppliers", supplierService.getAllSuppliers());
            return "suppliers";
        }

        supplierService.createSupplier(supplierDto);
        return "redirect:/admin/suppliers";
    }
}