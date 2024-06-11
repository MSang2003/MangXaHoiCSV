/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.pojo.Users;
import com.nms.services.UsersSevice;
import java.time.LocalDate;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class UserController {

    @Autowired
    private UsersSevice userService;

    @GetMapping("/users")
    public String userView(Model model) {
        model.addAttribute("user", new Users());
        model.addAttribute("listUsers", this.userService.getUsers());
        return "users";
    }

    @GetMapping("/userEditor")
    public String createUserView(Model model
    ) {
        model.addAttribute("user", new Users());
        return "userEditor";
    }

    @PostMapping("/userEditor")
    public String createUser(@ModelAttribute(value = "user") @Valid Users u,
            BindingResult rs, Model model) {
        if (rs.hasErrors()) {
            model.addAttribute("user", u);
            model.addAttribute("org.springframework.validation.BindingResult.user", rs);
            return "userEditor";
        }
        try {
            if (u.getUserID() != null && u.getUserID() > 0) {
                this.userService.updateUser(u);
            } else {
                this.userService.addUser(u);
            }
            return "redirect:/users";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "An error occurred while saving the user: " + ex.getMessage());
            model.addAttribute("user", u);
            model.addAttribute("org.springframework.validation.BindingResult.user", rs);
            return "userEditor";
        }
    }

    @GetMapping("/userEditor/{userID}")
    public String updateView(Model model, @PathVariable(value = "userID") int id) {
        model.addAttribute("user", this.userService.getUserById(id));

        return "userEditor";
    }
}
