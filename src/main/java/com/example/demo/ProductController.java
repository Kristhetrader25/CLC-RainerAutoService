package com.example.demo;

import com.example.demo.models.ProductForm;
import com.example.demo.models.ServicePackage;
import com.example.demo.services.ServicePackageDataService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
	
	 private final ServicePackageDataService productDataService;

	 // Constructor injection so Spring can wire the service
	 public ProductController(ServicePackageDataService productDataService) 
	 {
		 this.productDataService = productDataService;
	 }

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
        	 result.getFieldErrors().forEach(err ->
             System.out.println("Field error: " + err.getField() + " -> " + err.getDefaultMessage())
         );
            model.addAttribute("title", "Create Service Package - Rainier Auto Service");
            return "products-new";
        }

        /*System.out.println("Saving: code=" + form.getCode() +
                ", price=" + form.getBasePrice() +
                ", mins=" + form.getDurationMins());*/

        productDataService.save(form);
        
        return "redirect:/products/new?success";
    }

    // (Optional) to show a temp confirmation object on the success render:
    @ModelAttribute("categories")
    public String[] categories() {
        return new String[] {"Maintenance", "Repair", "Diagnostic"};
    }
}

