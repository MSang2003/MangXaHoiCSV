/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

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
import com.nms.pojo.Reactions;
import com.nms.pojo.ReactionsPK;
import com.nms.services.ReactionService;
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
public class ApiReactionController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UsersSevice userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ReactionService reactionService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @GetMapping("/post/{postID}/reactions")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<Object[]>> getReactionsByPostId(Model model, @PathVariable(value = "postID") int postID) {
        List<Object[]> reactions = this.reactionService.getReactionByPostId(postID);
        if (reactions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reactions);
    }

//    /post/postID/reactions
    @DeleteMapping("/post/{postID}/reaction")
    public ResponseEntity<?> deleteReactions(@PathVariable(value = "postID") int postID, Principal principal) {
        String username = principal.getName();
        Users user = this.userService.getUserByUsername(username);

        Reactions reaction = this.reactionService.getReactionByUserIDPostID(user.getUserID(), postID);
        if (reaction == null) {
            return new ResponseEntity<>("Reaction not found", HttpStatus.NOT_FOUND);
        }
        this.reactionService.deleteReaction(reaction);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @PostMapping(path = "/post/{postID}/reactions", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@PathVariable(value = "postID") int postID, @RequestParam Map<String, String> params, Principal principal) {

        String username = principal.getName();
        Users user = userService.getUserByUsername(username);
        Posts post = this.postService.getPostById(postID);

        Reactions r = new Reactions();
        r.setReactionType(params.get("reactionType"));
        r.setCreatedAt(new Date());
        r.setPosts(post);
        r.setUsers(user);
        ReactionsPK reactionsPK = new ReactionsPK(user.getUserID(), post.getPostID());
        r.setReactionsPK(reactionsPK);
        this.reactionService.createOrUpdateReaction(r);
        return ResponseEntity.ok(r);
    }

}
