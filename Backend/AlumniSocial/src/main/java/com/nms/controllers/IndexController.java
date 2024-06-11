/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.services.StatsService;
import com.nms.services.UsersSevice;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
public class IndexController {
    @Autowired
    private StatsService statsService;
    
    @GetMapping("/")
    public String index(Model modal,@RequestParam Map<String, String> params) {
        
        String year = params.getOrDefault("year", String.valueOf(LocalDate.now().getYear()));
        String period = params.getOrDefault("period", "MONTH");
        String role = params.getOrDefault("role", "Alumni");
        
        modal.addAttribute("statsAlumni", this.statsService.statsAlumniByYear());
        modal.addAttribute("statsUser", this.statsService.statsAmountUser(role,Integer.parseInt(year), period));
        modal.addAttribute("statsPost", this.statsService.statsPostByUser("Sang11",Integer.parseInt(year), period));

        return "index";
    }
}
