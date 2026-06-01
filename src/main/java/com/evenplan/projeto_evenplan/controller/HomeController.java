package com.evenplan.projeto_evenplan.controller;

// Alunas: Giulia Lain, Isabelle de Oliveira e Lauren Amici

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
