package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Rainier Auto Service Portal");
        return "index";
    }

    @GetMapping("/service-history")
    public String serviceHistory(Model model) {
        model.addAttribute("title", "Service History");
        return "service-history";
    }

    @GetMapping("/appointments")
    public String appointments(Model model) {
        model.addAttribute("title", "Appointments");
        return "appointments";
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("title", "Customer Lookup");
        return "customers";
    }
}
