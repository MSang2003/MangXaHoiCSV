/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.pojo.Posts;
import com.nms.pojo.Users;
import com.nms.services.PostService;
import com.nms.services.UsersSevice;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<Map<String, Object>> >getPosts() {
        List<Map<String, Object>> posts = this.postService.getPosts();
        
        return  ResponseEntity.ok(posts);
    }

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
    public void create(@RequestParam Map<String, String> params,@RequestPart MultipartFile[] file) {
        Posts p = new Posts();
        p.setContent(params.get("content"));
        p.setPostType(params.get("postType"));
        p.setCreatedAt(new Date());
        p.setUpdatedAt(new Date());

        
        if (file.length > 0) {
            p.setImageFile(file[0]);
        }

        Users u = this.userService.getUserById(Integer.parseInt(params.get("userID")));
        p.setUserID(u);

        this.postService.createOrUpdatePost(p);
    }

    //TODO: return ResponseEntity
}
