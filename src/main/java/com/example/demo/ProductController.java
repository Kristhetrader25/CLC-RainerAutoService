package com.example.demo;

import com.example.demo.models.ProductForm;
import com.example.demo.models.ServicePackage;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @GetMapping("/products/new")
    public String newProduct(Model model) {
        model.addAttribute("title", "Create Service Package - Rainier Auto Service");
        model.addAttribute("productForm", new ProductForm());
        return "products-new";
    }

    @PostMapping("/products")
    public String createProduct(@Valid @ModelAttribute("productForm") ProductForm form,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Create Service Package - Rainier Auto Service");
            return "products-new";
        }

        
        // For milestone 3, a simple redirect with a success flag.
        return "redirect:/products/new?success";
    }

    // (Optional) to show a temp confirmation object on the success render:
    @ModelAttribute("categories")
    public String[] categories() {
        return new String[] {"Maintenance", "Repair", "Diagnostic"};
    }
}

