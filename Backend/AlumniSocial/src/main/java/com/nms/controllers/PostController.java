/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.pojo.Users;
import com.nms.services.PostService;
import com.nms.services.UsersSevice;
import java.security.Principal;
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
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UsersSevice userService;

    @GetMapping("/post")
    public String userView(Model model, @RequestParam Map<String, String> params, Principal principal) {
        String isMyProfile = params.get("isMyProfile");
        Integer pageNumber = Integer.valueOf(params.get("pageNumber"));

        if ("trued".equals(isMyProfile)) {
            String username = principal.getName();
            Users user = userService.getUserByUsername(username);
//            model.addAttribute("posts", this.postService.getPosts(user.getUserID(),pageNumber));

        } else {
//            model.addAttribute("posts", this.postService.getPosts(null,pageNumber));

        }
        return "post";
    }

}
