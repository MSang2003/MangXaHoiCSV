/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.pojo.Posts;
import com.nms.pojo.Users;
import com.nms.services.PostService;
import com.nms.services.UsersSevice;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiPostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UsersSevice userService;

    @DeleteMapping("/post/{postID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "postID") int id) {
        this.postService.deletePostById(id);
    }

    
    
     @PostMapping(path = "/posts", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
                 MediaType.MULTIPART_FORM_DATA_VALUE

    })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params) {
        Posts p = new Posts();
        p.setContent(params.get("content"));
        p.setPostType(params.get("postType"));

        Users u = this.userService.getUserById(Integer.parseInt(params.get("userID")));
        p.setUserID(u);

        this.postService.createOrUpdatePost(p);
    }
    
    //TODO: return ResponseEntity

}
