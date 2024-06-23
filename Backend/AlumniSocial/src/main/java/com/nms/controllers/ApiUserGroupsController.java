/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.pojo.Usergroups;
import com.nms.pojo.Users;
import com.nms.services.CommentService;
import com.nms.services.PostService;
import com.nms.services.UserGroupsService;
import com.nms.services.UsersSevice;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ApiUserGroupsController {

    @Autowired
    private UserGroupsService groupService;

    @Autowired
    private UsersSevice userService;

    @Autowired
    private PostService postService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @GetMapping("/groups/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<Usergroups>> getGroups(Model model) {

        List<Usergroups> groups = this.groupService.getGroups();
        return ResponseEntity.ok(groups);
    }

    @PostMapping(path = "/groups/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestParam Map<String, String> params, Principal principal) {

        String username = principal.getName();
        Users requestUser = this.userService.getUserByUsername(username);

        Usergroups g = new Usergroups();

        g.setGroupName(params.get("groupName"));
        g.setCreatedAt(new Date());
        g.setUpdatedAt(new Date());

        this.groupService.createOrUpdateGroup(g);

        return ResponseEntity.ok("Group created successfully");

    }

//    @DeleteMapping("/comment/{id}")
//    public ResponseEntity<?> deleteComment(@PathVariable(value = "id") int id, Principal principal) {
//        Comments comment = commentService.getCommentById(id);
//        if (comment == null) {
//            return new ResponseEntity<>("Comment not found", HttpStatus.NOT_FOUND);
//        }
//
//        String username = principal.getName(); // get logged in username
//        Posts post = comment.getPostID();
//
//        // check if the logged in user is the one who commented or the one who posted
//        if (username.equals(comment.getUserID().getUserName()) || username.equals(post.getUserID().getUserName())) {
//            commentService.deleteComment(comment);
//            return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>("You don't have permission to delete this comment", HttpStatus.FORBIDDEN);
//        }
//    }
//
//    @PostMapping(path = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public ResponseEntity<?> create(@RequestParam Map<String, String> params, Principal principal) {
//
//        String username = principal.getName();
//        Users user = userService.getUserByUsername(username);
//        Posts post = this.postService.getPostById(Integer.parseInt(params.get("postID")));
//
//        Comments c = new Comments();
//        c.setContent(params.get("content"));
//        c.setCreatedAt(new Date());
//        c.setUserID(user);
//        c.setPostID(post);
//
//        this.commentService.createOrUpdateComment(c);
//        return ResponseEntity.ok(c);
//    }
//
}
