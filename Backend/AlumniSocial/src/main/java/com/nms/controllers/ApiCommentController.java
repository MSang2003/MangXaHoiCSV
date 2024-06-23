/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.pojo.Users;
import com.nms.services.CommentService;
import com.nms.services.PostService;
import com.nms.services.UsersSevice;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ApiCommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UsersSevice userService;

    @Autowired
    private PostService postService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @GetMapping("/post/{postID}/comments")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<Map<String, Object>>> getCommentsByPostId(Model model, @PathVariable(value = "postID") int postID) {
        List<Map<String, Object>> comments = commentService.getCommentsByPostId(postID);
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }
    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "id") int id, Principal principal) {
        Comments comment = commentService.getCommentById(id);
        if (comment == null) {
            return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
        }

        String username = principal.getName(); // get logged in username
        Posts post = comment.getPostID();

        // check if the logged in user is the one who commented or the one who posted
        if (username.equals(comment.getUserID().getUserName()) || username.equals(post.getUserID().getUserName())) {
            commentService.deleteComment(comment);
            return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>("You don't have permission to delete this comment", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(path = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestParam Map<String, String> params, Principal principal) {

        String username = principal.getName();
        Users user = userService.getUserByUsername(username);
        Posts post = this.postService.getPostById(Integer.parseInt(params.get("postID")));

        Comments c = new Comments();
        c.setContent(params.get("content"));
        c.setCreatedAt(new Date());
        c.setUserID(user);
        c.setPostID(post);

        this.commentService.createOrUpdateComment(c);
        return ResponseEntity.ok(c);
    }

    @PostMapping(path = "/comments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@PathVariable(value = "id") int id, @RequestParam Map<String, String> params, Principal principal) {

        String username = principal.getName();
        Comments c = this.commentService.getCommentById(id);

        if (c == null) {
            return new ResponseEntity<>("Comment not found", HttpStatus.BAD_REQUEST);
        }

        if (username.equals(c.getUserID().getUserName())) {
            c.setContent(params.get("content"));
            c.setUpdatedAt(new Date());

            this.commentService.createOrUpdateComment(c);
            return ResponseEntity.ok(c);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }
}
